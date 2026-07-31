package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderStatusService {

    private final RestClient restClient;

    @Autowired
    public OrderStatusService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedOrderStatuses {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public OrderStatusList OrderStatuses;
    }

    private static class OrderStatusList {
        @com.fasterxml.jackson.annotation.JsonProperty("orderStatus")
        public List<OrderStatusDto> OrderStatus;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /api/orderStatus  (returns all OrderStatuses)
     */
    public List<OrderStatusDto> findAll() {
        EmbeddedOrderStatuses response = restClient.get()
                .uri("/api/orderStatus")
                .retrieve()
                .body(EmbeddedOrderStatuses.class);

        return response != null 
                && response.OrderStatuses != null 
                && response.OrderStatuses.OrderStatus != null
                    ? response.OrderStatuses.OrderStatus
                    : List.of();
    }
    
    public Optional<OrderStatusDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/orderStatus/{id}", id)
                        .retrieve()
                        .body(OrderStatusDto.class)
        );
    }     
 
}