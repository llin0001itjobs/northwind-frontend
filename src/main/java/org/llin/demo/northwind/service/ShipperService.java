package org.llin.demo.northwind.service;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.ShipperDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ShipperService {

    private final RestClient restClient;

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

    public Optional<ShipperDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/shipper/{id}", id)
                        .retrieve()
                        .body(ShipperDto.class)
        );
    }

    /**
     * GET /Shipper  (returns all Shippers)
     */
    public List<ShipperDto> findAll() {
        EmbeddedShippers response = restClient.get()
                .uri("/shipper")
                .retrieve()
                .body(EmbeddedShippers.class);

        return response != null 
                && response.Shippers != null 
                && response.Shippers.Shipper != null
                    ? response.Shippers.Shipper
                    : List.of();
    }

    public ShipperDto create(ShipperDto ShipperDto) {
        return restClient.post()
                .uri("/shipper")
                .body(ShipperDto)
                .retrieve()
                .body(ShipperDto.class);
    }

    public ShipperDto update(Integer id, ShipperDto ShipperDto) {
        return restClient.put()
                .uri("/shipper/{id}", id)
                .body(ShipperDto)
                .retrieve()
                .body(ShipperDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/shipper/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
