package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PaymentTypeService {

    private final RestClient restClient;

    @Autowired
    public PaymentTypeService(RestClient restClient) {
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
                .uri("/role")
                .retrieve()
                .body(EmbeddedRoles.class);

        return response != null 
                && response.Roles != null 
                && response.Roles.Role != null
                    ? response.Roles.Role
                    : List.of();
    }
 
}