package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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
        public OrderTaxStatusList TypeStates;
    }

    private static class OrderTaxStatusList {
        @com.fasterxml.jackson.annotation.JsonProperty("orderTaxStatus")
        public List<OrderTaxStatusDto> OrderTaxStatus;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /typeState  (returns all TypeStates)
     */
    public List<OrderTaxStatusDto> findAll() {
        EmbeddedTypeStates response = restClient.get()
                .uri("/orderTaxStatus")
                .retrieve()
                .body(EmbeddedTypeStates.class);

        return response != null 
                && response.TypeStates != null 
                && response.TypeStates.OrderTaxStatus != null
                    ? response.TypeStates.OrderTaxStatus
                    : List.of();
    }
 
}