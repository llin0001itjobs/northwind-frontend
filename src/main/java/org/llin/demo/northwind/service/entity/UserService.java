package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserService {

    private final RestClient restClient;

    @Autowired
    public UserService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedUsers {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public UserList Users;
    }

    private static class UserList {
        @com.fasterxml.jackson.annotation.JsonProperty("user")
        public List<UserDto> User;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<UserDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/user/{id}", id)
                        .retrieve()
                        .body(UserDto.class)
        );
    }
    
    /**
     * GET /User  (returns all Users)
     */
    public List<UserDto> findAll() {
        EmbeddedUsers response = restClient.get()
                .uri("/api/user")
                .retrieve()
                .body(EmbeddedUsers.class);

        return response != null 
                && response.Users != null 
                && response.Users.User != null
                    ? response.Users.User
                    : List.of();
    }

    public UserDto create(UserDto UserDto) {
        return restClient.post()
                .uri("/api/user/{id}")
                .body(UserDto)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto update(Integer id, UserDto UserDto) {
        return restClient.put()
                .uri("/api/user/{id}", id)
                .body(UserDto)
                .retrieve()
                .body(UserDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/user/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public Optional<UserDto> findByUsername(String username) {
        if (username == null) return Optional.empty();

        return findByObject(username, "username", "findByUsername");
    }

    public Optional<UserDto> findByEmail(String email) {
        if (email == null) return Optional.empty();

        return findByObject(email, "email", "findByEmail");
    }  
    
    public Optional<UserDto> findByVerificationToken(String token) {
        if (token == null) return Optional.empty();

        return findByObject(token, "token", "findByVerificationToken");
    }     
    
    private Optional<UserDto> findByObject(Object o, String paramNam, String path) {
    	if (o == null) return Optional.empty();
    	
        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/user/search/" + path + "?" + paramNam + "={" + paramNam + "}", o)
                        .retrieve()
                        .body(UserDto.class)
        ); 
    }
}