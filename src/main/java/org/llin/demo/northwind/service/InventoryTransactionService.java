package org.llin.demo.northwind.service;

import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryTransactionService {

    private final RestClient restClient;

    public InventoryTransactionService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedInventoryTransactions {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public InventoryTransactionList InventoryTransactions;
    }

    private static class InventoryTransactionList {
        @com.fasterxml.jackson.annotation.JsonProperty("inventoryTransaction")
        public List<InventoryTransactionDto> InventoryTransaction;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<InventoryTransactionDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/inventoryTransaction/{id}", id)
                        .retrieve()
                        .body(InventoryTransactionDto.class)
        );
    }

    /**
     * GET /InventoryTransaction  (returns all InventoryTransactions)
     */
    public List<InventoryTransactionDto> findAll() {
        EmbeddedInventoryTransactions response = restClient.get()
                .uri("/inventoryTransaction")
                .retrieve()
                .body(EmbeddedInventoryTransactions.class);

        return response != null 
                && response.InventoryTransactions != null 
                && response.InventoryTransactions.InventoryTransaction != null
                    ? response.InventoryTransactions.InventoryTransaction
                    : List.of();
    }

    public InventoryTransactionDto create(InventoryTransactionDto InventoryTransactionDto) {
        return restClient.post()
                .uri("/inventoryTransaction")
                .body(InventoryTransactionDto)
                .retrieve()
                .body(InventoryTransactionDto.class);
    }

    public InventoryTransactionDto update(Integer id, InventoryTransactionDto InventoryTransactionDto) {
        return restClient.put()
                .uri("/inventoryTransaction/{id}", id)
                .body(InventoryTransactionDto)
                .retrieve()
                .body(InventoryTransactionDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/inventoryTransaction/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}