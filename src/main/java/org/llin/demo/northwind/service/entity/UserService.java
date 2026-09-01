package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.UserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class UserService {

    private final RestClient restClient;

    public UserService(RestClient restClient) {
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
        if (id == null) return Optional.empty();
        try {
            return Optional.ofNullable(
                    restClient.get()
                            .uri("user/{id}", id)
                            .retrieve()
                            .body(UserDto.class));
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    public List<UserDto> findAll() {
        EmbeddedUsers response = restClient.get()
                .uri("user")
                .retrieve()
                .body(EmbeddedUsers.class);

        return response != null
                && response.embedded != null
                && response.embedded.users != null
                ? response.embedded.users
                : List.of();
    }

    public UserDto create(UserDto user) {
        return restClient.post()
                .uri("user")
                .body(user)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto update(Integer id, UserDto user) {
        return restClient.put()
                .uri("user/{id}", id)
                .body(user)
                .retrieve()
                .body(UserDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("user/{id}", id)
                .retrieve()
                .toBodilessEntity();
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

		try {
			return restClient.get().uri(uriBuilder -> uriBuilder
					.path("user/search/{method}")
					.queryParam(paramName, value)
					.build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<UserDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}
}