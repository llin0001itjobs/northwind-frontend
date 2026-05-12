package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.InventoryTransactionTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class InventoryTransactionTypeService {

    private final RestClient restClient;

    @Autowired
    public InventoryTransactionTypeService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedInventoryTransactionTypes {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public InventoryTransactionTypeList InventoryTransactionTypes;
    }

    private static class InventoryTransactionTypeList {
        @com.fasterxml.jackson.annotation.JsonProperty("inventoryTransactionType")
        public List<InventoryTransactionTypeDto> InventoryTransactionType;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /InventoryTransactionType  (returns all InventoryTransactionTypes)
     */
    public List<InventoryTransactionTypeDto> findAll() {
        EmbeddedInventoryTransactionTypes response = restClient.get()
                .uri("/inventoryTransactionType")
                .retrieve()
                .body(EmbeddedInventoryTransactionTypes.class);

        return response != null 
                && response.InventoryTransactionTypes != null 
                && response.InventoryTransactionTypes.InventoryTransactionType != null
                    ? response.InventoryTransactionTypes.InventoryTransactionType
                    : List.of();
    }


}