package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.dto.TypeStateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TypeStateService {

    private final RestClient restClient;

    @Autowired
    public TypeStateService(@Qualifier("northwindDataClient") RestClient restClient) {
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
        @com.fasterxml.jackson.annotation.JsonProperty("typeStates")
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
                .uri("typeState")
                .retrieve()
                .body(EmbeddedTypeStates.class);

        return response != null 
                && response.TypeStates != null 
                && response.TypeStates.TypeState != null
                    ? response.TypeStates.TypeState
                    : List.of();
    }
 
    public List<TypeStateDto> findById(Integer id) {
		if (id == null)
			return List.of();

		List<TypeStateDto> list = new ArrayList<>();

		TypeStateDto dto = restClient.get().uri("typeState/{id}", id).retrieve().body(TypeStateDto.class);

		if (dto != null)
			list.add(dto);

		return list;
    }
        
}