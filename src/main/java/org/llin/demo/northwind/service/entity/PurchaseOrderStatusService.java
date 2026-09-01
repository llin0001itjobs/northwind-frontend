package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.PurchaseOrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PurchaseOrderStatusService {

    private final RestClient restClient;

    @Autowired
    public PurchaseOrderStatusService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedPurchaseOrderStatuss {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public PurchaseOrderStatusList PurchaseOrderStatuses;
    }

    private static class PurchaseOrderStatusList {
        @com.fasterxml.jackson.annotation.JsonProperty("purchaseOrderStatus")
        public List<PurchaseOrderStatusDto> PurchaseOrderStatus;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /PurchaseOrderStatus  (returns all PurchaseOrderStatuss)
     */
    public List<PurchaseOrderStatusDto> findAll() {
        EmbeddedPurchaseOrderStatuss response = restClient.get()
                .uri("purchaseOrderStatus")
                .retrieve()
                .body(EmbeddedPurchaseOrderStatuss.class);

        return response != null 
                && response.PurchaseOrderStatuses != null 
                && response.PurchaseOrderStatuses.PurchaseOrderStatus != null
                    ? response.PurchaseOrderStatuses.PurchaseOrderStatus
                    : List.of();
    }
 
    public Optional<PurchaseOrderStatusDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("purchaseOrderStatus/{id}", id)
                        .retrieve()
                        .body(PurchaseOrderStatusDto.class)
        );
    }
    
}