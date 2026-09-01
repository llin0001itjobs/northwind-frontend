package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.ShipperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
@Service
public class ShipperService {

    private final RestClient restClient;

    @Autowired
    public ShipperService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedShippers {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public ShipperList Shippers;
    }

    private static class ShipperList {
        @com.fasterxml.jackson.annotation.JsonProperty("shipper")
        public List<ShipperDto> Shipper;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /Shipper  (returns all Shippers)
     */
    public List<ShipperDto> findAll() {
        EmbeddedShippers response = restClient.get()
                .uri("shipper")
                .retrieve()
                .body(EmbeddedShippers.class);

        return response != null 
                && response.Shippers != null 
                && response.Shippers.Shipper != null
                    ? response.Shippers.Shipper
                    : List.of();
    }

    public Optional<ShipperDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("shipper/{id}", id)
                        .retrieve()
                        .body(ShipperDto.class)
        );
    }

    public ShipperDto create(ShipperDto ShipperDto) {
        return restClient.post()
                .uri("shipper")
                .body(ShipperDto)
                .retrieve()
                .body(ShipperDto.class);
    }

    public ShipperDto update(Integer id, ShipperDto ShipperDto) {
        return restClient.put()
                .uri("shipper/{id}", id)
                .body(ShipperDto)
                .retrieve()
                .body(ShipperDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("shipper/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public List<ShipperDto> findAllByLastName(String lastName) {
    	return findByObject(lastName, "lastName", "findAllByLastName");    	
    }
    
    public List<ShipperDto> findByJobTitle(String jobTitle) {
    	return findByObject(jobTitle, "jobTitle", "findByJobTitle"); 
    }
    
    public List<ShipperDto> findByEmailAddress(String emailAddress) {
    	return findByObject(emailAddress, "emailAddress", "findByEmailAddress");    	
    }
	
    public List<ShipperDto> findByLastNameContaining(String lastName) {
    	return findByObject(lastName, "lastName", "findByLastNameContaining");    	
    }
    
    public List<ShipperDto> findByFirstNameContaining(String firstName) {
    	return findByObject(firstName, "firstName", "findByFirstNameContaining");
    }
    
    public List<ShipperDto> findByJobTitleContaining(String jobTitle) {
    	return findByObject(jobTitle, "jobTitle", "findByJobTitleContaining");
    }
    
    private List<ShipperDto> findByObject(Object value, String paramName, String searchMethod) {
        if (value == null) {
            return Collections.emptyList();
        }

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("shipper/search/{method}")
                            .queryParam(paramName, value)
                            .build(searchMethod))
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ShipperDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return new ArrayList<>();
        }
    }
	
}
