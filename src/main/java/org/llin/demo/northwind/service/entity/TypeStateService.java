package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.TypeStateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TypeStateService {

    private final RestClient restClient;

    @Autowired
    public TypeStateService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedTypeStates {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public TypeStateList TypeStates;
    }

    private static class TypeStateList {
        @com.fasterxml.jackson.annotation.JsonProperty("typeState")
        public List<TypeStateDto> TypeState;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /typeState  (returns all TypeStates)
     */
    public List<TypeStateDto> findAll() {
        EmbeddedTypeStates response = restClient.get()
                .uri("/api/typeState")
                .retrieve()
                .body(EmbeddedTypeStates.class);

        return response != null 
                && response.TypeStates != null 
                && response.TypeStates.TypeState != null
                    ? response.TypeStates.TypeState
                    : List.of();
    }
 
    public Optional<TypeStateDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/typeState/{id}", id)
                        .retrieve()
                        .body(TypeStateDto.class)
        );
    }
        
}