package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderTaxStatusService {

    private final RestClient restClient;

    @Autowired
    public OrderTaxStatusService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedTypeStates {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public OrderTaxStatusList OrderTaxStatuses;
    }

    private static class OrderTaxStatusList {
        @com.fasterxml.jackson.annotation.JsonProperty("orderTaxStatus")
        public List<OrderTaxStatusDto> OrderTaxStatus;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public List<OrderTaxStatusDto> findAll() {
        EmbeddedTypeStates response = restClient.get()
                .uri("/api/orderTaxStatus")
                .retrieve()
                .body(EmbeddedTypeStates.class);

        return response != null 
                && response.OrderTaxStatuses != null 
                && response.OrderTaxStatuses.OrderTaxStatus != null
                    ? response.OrderTaxStatuses.OrderTaxStatus
                    : List.of();
    }
    
    public Optional<OrderTaxStatusDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/orderTaxStatus/{id}", id)
                        .retrieve()
                        .body(OrderTaxStatusDto.class)
        );
    }    
 
}