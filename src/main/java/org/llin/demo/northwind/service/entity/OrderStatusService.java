package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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
     * GET /typeState  (returns all OrderStatuses)
     */
    public List<OrderStatusDto> findAll() {
        EmbeddedOrderStatuses response = restClient.get()
                .uri("/orderStatus")
                .retrieve()
                .body(EmbeddedOrderStatuses.class);

        return response != null 
                && response.OrderStatuses != null 
                && response.OrderStatuses.OrderStatus != null
                    ? response.OrderStatuses.OrderStatus
                    : List.of();
    }
 
}