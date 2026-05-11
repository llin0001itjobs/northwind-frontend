package org.llin.demo.northwind.service;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.SupplierDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SupplierService {

    private final RestClient restClient;

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
        @com.fasterxml.jackson.annotation.JsonProperty("upplier")
        public List<SupplierDto> Supplier;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<SupplierDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/supplier/{id}", id)
                        .retrieve()
                        .body(SupplierDto.class)
        );
    }

    /**
     * GET /Supplier  (returns all Suppliers)
     */
    public List<SupplierDto> findAll() {
        EmbeddedSuppliers response = restClient.get()
                .uri("/supplier")
                .retrieve()
                .body(EmbeddedSuppliers.class);

        return response != null 
                && response.Suppliers != null 
                && response.Suppliers.Supplier != null
                    ? response.Suppliers.Supplier
                    : List.of();
    }

    public SupplierDto create(SupplierDto SupplierDto) {
        return restClient.post()
                .uri("/supplier")
                .body(SupplierDto)
                .retrieve()
                .body(SupplierDto.class);
    }

    public SupplierDto update(Integer id, SupplierDto SupplierDto) {
        return restClient.put()
                .uri("/supplier/{id}", id)
                .body(SupplierDto)
                .retrieve()
                .body(SupplierDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/supplier/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
