package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.llin.demo.northwind.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class UserService {

	@Autowired
	private PropertyDefaultProperties props; 
	
	private final RestClient restClient;

	public UserService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	private static class EmbeddedUsers {
		@JsonProperty("_embedded")
		public UserList embedded;
	}

	private static class UserList {
		@JsonProperty("users")
		public List<UserDto> users;
	}

	public Optional<UserDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();
		try {
			return Optional.ofNullable(restClient.get().uri("user/{id}", id).retrieve().body(UserDto.class));
		} catch (HttpClientErrorException.NotFound e) {
			return Optional.empty();
		}
	}

	public List<UserDto> findAll() {
		EmbeddedUsers response = restClient.get().uri("user").retrieve().body(EmbeddedUsers.class);

		return response != null && response.embedded != null && response.embedded.users != null
				? response.embedded.users
				: List.of();
	}

	public UserDto create(UserDto user) throws IllegalStateException {
		
		if (findByEmail(user.email()).size() > 0) {
			throw new IllegalStateException("Email is not unique.");
		}
		
		// 1. Build a map structure that matches the exact flat JSON layout Spring Data
		// REST expects
		java.util.Map<String, Object> payload = new java.util.HashMap<>();
		payload.put("username", user.username());
		payload.put("password", user.password());
		payload.put("email", user.email());
		payload.put("enabled", user.enabled());

		// 2. Map the roles to pure URI strings instead of full objects
		if (user.roles() != null) {
			java.util.List<String> roleUris = user.roles().stream()
					.map(role -> props.getApp().getData().getApiUri() + "role/" + role.id()) // Make sure this matches
																								// your exact backend
																								// role path
					.toList();
			payload.put("roles", roleUris);
		}

		// 3. Send the custom payload map
	    try {
	        return restClient.post()
	                .uri("user")
	                .body(payload)
	                .retrieve()
	                .body(UserDto.class);
	    } catch (HttpClientErrorException.Conflict ex) {
	        // Catch 409 Conflict if backend throws an integrity violation error
	        throw new IllegalStateException("Registration failed: Email was taken concurrently.", ex);
	    }
	}

	public UserDto update(Integer id, UserDto user) {
				
	    // 1. Build a map structure that matches the exact flat JSON layout the backend REST expects
	    java.util.Map<String, Object> payload = new java.util.HashMap<>();
	    payload.put("id", id); // Add the ID since it is an update operation
	    payload.put("username", user.username());
	    payload.put("password", user.password());
	    payload.put("email", user.email());
	    payload.put("enabled", user.enabled());

	    // 2. Map the roles to pure URI strings instead of full objects
	    if (user.roles() != null) {
	        java.util.List<String> roleUris = user.roles().stream()
	            .map(role -> props.getApp().getData().getApiUri() + "role/" + role.id())
	            .toList();
	        payload.put("roles", roleUris);
	    }

	    // 3. Send the custom payload map via PUT
	    return restClient.put()
	        .uri("user/{id}", id)
	        .body(payload)
	        .retrieve()
	        .body(UserDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("user/{id}", id).retrieve().toBodilessEntity();
	}

	public List<UserDto> findByUsername(String username) {
		return findByObject(username, "username", "findByUsername");
	}

	public List<UserDto> findByEmail(String email) {
		return findByObject(email, "email", "findByEmail");
	}

	public List<UserDto> findByVerificationToken(String token) {
		return findByObject(token, "token", "findByVerificationToken");
	}

	private List<UserDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		// Deserialize to the EmbeddedUsers container object
		EmbeddedUsers response = restClient.get().uri(
				uriBuilder -> uriBuilder.path("user/search/{method}").queryParam(paramName, value).build(searchMethod))
				.retrieve().body(EmbeddedUsers.class);

		// Return the list cleanly or an empty list if nothing was found
		return response != null && response.embedded != null && response.embedded.users != null
				? response.embedded.users
				: Collections.emptyList();
	}
}