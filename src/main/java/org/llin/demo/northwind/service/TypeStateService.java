package org.llin.demo.northwind.service;

import org.llin.demo.northwind.dto.TypeStateDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TypeStateService {

    private final RestClient restClient;

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
                .uri("/typeState")
                .retrieve()
                .body(EmbeddedTypeStates.class);

        return response != null 
                && response.TypeStates != null 
                && response.TypeStates.TypeState != null
                    ? response.TypeStates.TypeState
                    : List.of();
    }
 
}