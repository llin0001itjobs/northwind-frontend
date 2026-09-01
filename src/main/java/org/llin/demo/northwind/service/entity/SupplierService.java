package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class SupplierService {

    private final RestClient restClient;

    @Autowired
    public SupplierService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedSuppliers {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public SupplierList Suppliers;
    }

    private static class SupplierList {
        @com.fasterxml.jackson.annotation.JsonProperty("supplier")
        public List<SupplierDto> Supplier;
    }

    // ==================================================================
    // Public methods
    // ==================================================================
    /**
     * GET /Supplier  (returns all Suppliers)
     */
    public List<SupplierDto> findAll() {
        EmbeddedSuppliers response = restClient.get()
                .uri("supplier")
                .retrieve()
                .body(EmbeddedSuppliers.class);

        return response != null 
                && response.Suppliers != null 
                && response.Suppliers.Supplier != null
                    ? response.Suppliers.Supplier
                    : List.of();
    }
    
    public Optional<SupplierDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("supplier/{id}", id)
                        .retrieve()
                        .body(SupplierDto.class)
        );
    }

    public SupplierDto create(SupplierDto SupplierDto) {
        return restClient.post()
                .uri("supplier")
                .body(SupplierDto)
                .retrieve()
                .body(SupplierDto.class);
    }

    public SupplierDto update(Integer id, SupplierDto SupplierDto) {
        return restClient.put()
                .uri("supplier/{id}", id)
                .body(SupplierDto)
                .retrieve()
                .body(SupplierDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("supplier/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public List<SupplierDto> findAllByLastName(String lastName) {
    	return findByObject(lastName, "lastName", "findAllByLastName");    	
    }
    
    public List<SupplierDto> findByJobTitle(String jobTitle) {
    	return findByObject(jobTitle, "jobTitle", "findByJobTitle"); 
    }
    
    public List<SupplierDto> findByEmailAddress(String emailAddress) {
    	return findByObject(emailAddress, "emailAddress", "findByEmailAddress");    	
    }
	
    public List<SupplierDto> findByLastNameContaining(String lastName) {
    	return findByObject(lastName, "lastName", "findByLastNameContaining");    	
    }
    
    public List<SupplierDto> findByFirstNameContaining(String firstName) {
    	return findByObject(firstName, "firstName", "findByFirstNameContaining");
    }
    
    public List<SupplierDto> findByJobTitleContaining(String jobTitle) {
    	return findByObject(jobTitle, "jobTitle", "findByJobTitleContaining");
    }
        
    private List<SupplierDto> findByObject(Object value, String paramName, String searchMethod) {
        if (value == null) {
            return Collections.emptyList();
        }

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("supplier/search/{method}")
                            .queryParam(paramName, value)
                            .build(searchMethod))
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<SupplierDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptyList();
        }
    }
    
}
