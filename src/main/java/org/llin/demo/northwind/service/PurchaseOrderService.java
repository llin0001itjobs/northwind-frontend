package org.llin.demo.northwind.service;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.springframework.web.client.RestClient;

public class PurchaseOrderService {
	 private final RestClient restClient;

	    public PurchaseOrderService(RestClient restClient) {
	        this.restClient = restClient;
	    }

	    // ==================================================================
	    // Helper classes for HAL _embedded wrapper (placed at class level)
	    // ==================================================================

	    private static class EmbeddedPurchaseOrders {
	        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
	        public PurchaseOrderList PurchaseOrders;
	    }

	    private static class PurchaseOrderList {
	        @com.fasterxml.jackson.annotation.JsonProperty("purchaseOrder")
	        public List<PurchaseOrderDto> PurchaseOrder;
	    }

	    // ==================================================================
	    // Public methods
	    // ==================================================================

	    public Optional<PurchaseOrderDto> findById(Integer id) {
	        if (id == null) return Optional.empty();

	        return Optional.ofNullable(
	                restClient.get()
	                        .uri("/purchaseOrder/{id}", id)
	                        .retrieve()
	                        .body(PurchaseOrderDto.class)
	        );
	    }

	    /**
	     * GET /purchaseOrder  (returns all PurchaseOrders)
	     */
	    public List<PurchaseOrderDto> findAll() {
	        EmbeddedPurchaseOrders response = restClient.get()
	                .uri("/purchaseOrder")
	                .retrieve()
	                .body(EmbeddedPurchaseOrders.class);

	        return response != null 
	                && response.PurchaseOrders != null 
	                && response.PurchaseOrders.PurchaseOrder != null
	                    ? response.PurchaseOrders.PurchaseOrder
	                    : List.of();
	    }

	    public PurchaseOrderDto create(PurchaseOrderDto PurchaseOrderDto) {
	        return restClient.post()
	                .uri("/purchaseOrder")
	                .body(PurchaseOrderDto)
	                .retrieve()
	                .body(PurchaseOrderDto.class);
	    }

	    public PurchaseOrderDto update(Integer id, PurchaseOrderDto PurchaseOrderDto) {
	        return restClient.put()
	                .uri("/purchaseOrder/{id}", id)
	                .body(PurchaseOrderDto)
	                .retrieve()
	                .body(PurchaseOrderDto.class);
	    }

	    public void deleteById(Integer id) {
	        restClient.delete()
	                .uri("/purchaseOrder/{id}", id)
	                .retrieve()
	                .toBodilessEntity();
	    }
}
