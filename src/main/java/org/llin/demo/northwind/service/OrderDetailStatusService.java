package org.llin.demo.northwind.service;

import org.llin.demo.northwind.dto.OrderDetailStatusDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderDetailStatusService {

    private final RestClient restClient;

    public OrderDetailStatusService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedOrderDetailStatuses {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public OrderDetailStatusList OrderDetailStatuses;
    }

    private static class OrderDetailStatusList {
        @com.fasterxml.jackson.annotation.JsonProperty("orderDetailStatus")
        public List<OrderDetailStatusDto> OrderDetailStatus;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /typeState  (returns all OrderDetailStatuses)
     */
    public List<OrderDetailStatusDto> findAll() {
        EmbeddedOrderDetailStatuses response = restClient.get()
                .uri("/orderDetailStatus")
                .retrieve()
                .body(EmbeddedOrderDetailStatuses.class);

        return response != null 
                && response.OrderDetailStatuses != null 
                && response.OrderDetailStatuses.OrderDetailStatus != null
                    ? response.OrderDetailStatuses.OrderDetailStatus
                    : List.of();
    }
 
}