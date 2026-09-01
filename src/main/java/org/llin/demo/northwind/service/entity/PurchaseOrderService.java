package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class PurchaseOrderService {
	private final RestClient restClient;

	@Autowired
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
		if (id == null)
			return Optional.empty();

		return Optional
				.ofNullable(restClient.get().uri("purchaseOrder/{id}", id).retrieve().body(PurchaseOrderDto.class));
	}

	/**
	 * GET /purchaseOrder (returns all PurchaseOrders)
	 */
	public List<PurchaseOrderDto> findAll() {
		EmbeddedPurchaseOrders response = restClient.get().uri("purchaseOrder").retrieve()
				.body(EmbeddedPurchaseOrders.class);

		return response != null && response.PurchaseOrders != null && response.PurchaseOrders.PurchaseOrder != null
				? response.PurchaseOrders.PurchaseOrder
				: List.of();
	}

	public PurchaseOrderDto create(PurchaseOrderDto PurchaseOrderDto) {
		return restClient.post().uri("purchaseOrder").body(PurchaseOrderDto).retrieve().body(PurchaseOrderDto.class);
	}

	public PurchaseOrderDto update(Integer id, PurchaseOrderDto PurchaseOrderDto) {
		return restClient.put().uri("purchaseOrder/{id}", id).body(PurchaseOrderDto).retrieve()
				.body(PurchaseOrderDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("purchaseOrder/{id}", id).retrieve().toBodilessEntity();
	}
	
	public List<PurchaseOrderDto> findBySupplierId(Integer id) {
		return findByObject(id, "id", "findBySupplierId");
	}
	
	public List<PurchaseOrderDto> findByCreatedById(Integer id) {
		return findByObject(id, "id", "findByCreatedById");
	}
	
	public List<PurchaseOrderDto> findByApprovedById(Integer id) {
		return findByObject(id, "id", "findByApprovedById");
	}
	
	public List<PurchaseOrderDto> findBySubmittedById(Integer id){
		return findByObject(id, "id", "findBySubmittedById");
	}
	
	public List<PurchaseOrderDto> findByOrderStatusId(Integer id) {
		return findByObject(id, "id", "findByOrderStatusId");		
	}

    // Date ranges (very common for PO reports)
	public List<PurchaseOrderDto> findByCreationDateBetweenOrderByCreationDateAsc(LocalDateTime start, LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByCreationDateBetweenOrderByCreationDateAsc");
	}
	
	public List<PurchaseOrderDto> findBySubmittedDateBetweenOrderBySubmittedDateAsc(LocalDateTime start, LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findBySubmittedDateBetweenOrderBySubmittedDateAsc");
	}
	
	public List<PurchaseOrderDto> findByExpectedDateBetweenOrderByExpectedDateAsc(LocalDateTime start, LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByExpectedDateBetweenOrderByExpectedDateAsc");
	}
	
	public List<PurchaseOrderDto> findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDateTime start, LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByPaymentDateBetweenOrderByPaymentDateAsc");
	}

    // Money ranges
	public List<PurchaseOrderDto> findByShippingFeeBetweenOrderByShippingFeeAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByShippingFeeBetweenOrderByShippingFeeAsc");
	}
	
	public List<PurchaseOrderDto> findByTaxesBetweenOrderByTaxesAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByTaxesBetweenOrderByTaxesAsc");
	}
	
	public List<PurchaseOrderDto> findByPaymentAmountBetweenOrderByPaymentAmountAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByPaymentAmountBetweenOrderByPaymentAmountAsc");
	}

	public List<PurchaseOrderDto> findByNotesContaining(String notes) {
		return findByObject(notes, "notes", "findByNotesContaining");
	}
	
    private List<PurchaseOrderDto> findByObject(Object value, String paramName, String searchMethod) {
        if (value == null) {
            return Collections.emptyList();
        }

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("purchaseOrder/search/{method}")
                            .queryParam(paramName, value)
                            .build(searchMethod))
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<PurchaseOrderDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptyList();
        }
    }
    
    private List<PurchaseOrderDto> findWithTwoParameters(Object param1, String paramName1, 
			 											 Object param2, String paramName2, String path) {
        try {    	
			return restClient.get()
			.uri(uriBuilder -> uriBuilder.path("purchaseOrder/search/" + path)
			.queryParam(paramName1, param1)
			.queryParam(paramName2, param2)
			.build())
			.retrieve()
			.body(new ParameterizedTypeReference<List<PurchaseOrderDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptyList();
        }			
    }	
    
}
