package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RoleService {

    private final RestClient restClient;

    @Autowired
    public RoleService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedRoles {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public RoleList Roles;
    }

    private static class RoleList {
        @com.fasterxml.jackson.annotation.JsonProperty("role")
        public List<RoleDto> Role;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /Role  (returns all Roles)
     */
    public List<RoleDto> findAll() {
        EmbeddedRoles response = restClient.get()
                .uri("/api/role")
                .retrieve()
                .body(EmbeddedRoles.class);

        return response != null 
                && response.Roles != null 
                && response.Roles.Role != null
                    ? response.Roles.Role
                    : List.of();
    }
    
    public Optional<RoleDto> findByRoleType(String roleType) {
        if (roleType == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/role/search/findByRoleType?roleType={roleType}", roleType)
                        .retrieve()
                        .body(RoleDto.class)
        );
    }
 
}