package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.LabelDoubleValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.PurchaseOrderDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PurchaseOrderDetailService {
	private final RestClient restClient;

	@Autowired
	public PurchaseOrderDetailService(RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedPurchaseOrderDetails {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public PurchaseOrderDetailList PurchaseOrderDetails;
	}

	private static class PurchaseOrderDetailList {
		@com.fasterxml.jackson.annotation.JsonProperty("purchaseOrderDetail")
		public List<PurchaseOrderDetailDto> PurchaseOrderDetail;
	}
	
		private static class EmbeddedLabelValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongList LabelValueLongs;
	}

	private static class LabelValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLong")
		public List<LabelValueLongDto> LabelValueLong;
	}
	
	private static class EmbeddedLabelDoubleValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelDoubleValueLongList LabelDoubleValueLongs;
	}

	private static class LabelDoubleValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelDoubleValueLong")
		public List<LabelDoubleValueLongDto> LabelDoubleValueLong;
	}
	
	

	// ==================================================================
	// Public methods
	// ==================================================================

	public Optional<PurchaseOrderDetailDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional.ofNullable(
				restClient.get().uri("/api/purchaseOrderDetail/{id}", id).retrieve().body(PurchaseOrderDetailDto.class));
	}

	/**
	 * GET /api/purchaseOrderDetail (returns all PurchaseOrderDetails)
	 */
	public List<PurchaseOrderDetailDto> findAll() {
		EmbeddedPurchaseOrderDetails response = restClient.get().uri("/api/purchaseOrderDetail").retrieve()
				.body(EmbeddedPurchaseOrderDetails.class);

		return response != null && response.PurchaseOrderDetails != null
				&& response.PurchaseOrderDetails.PurchaseOrderDetail != null
						? response.PurchaseOrderDetails.PurchaseOrderDetail
						: List.of();
	}

	public PurchaseOrderDetailDto create(PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.post().uri("/api/purchaseOrderDetail").body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public PurchaseOrderDetailDto update(Integer id, PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.put().uri("/api/purchaseOrderDetail/{id}", id).body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("/api/purchaseOrderDetail/{id}", id).retrieve().toBodilessEntity();
	}
	
	public List<LabelValueLongDto> shippingFeePerMonth() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/api/purchaseOrderDetail/shipping-fee-per-month").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }
	
	public List<LabelDoubleValueLongDto> quantityPerUnitCost() {
		EmbeddedLabelDoubleValueLongs response = restClient.get()
    			.uri("/api/purchaseOrderDetail/quantity-per-unit-cost").retrieve()
    			.body(EmbeddedLabelDoubleValueLongs.class);
    	return response != null && response.LabelDoubleValueLongs != null && response.LabelDoubleValueLongs.LabelDoubleValueLong != null
				? response.LabelDoubleValueLongs.LabelDoubleValueLong
				: List.of();		
    }
	
    public List<PurchaseOrderDetailDto> findByPurchaseOrderId(Integer id) {
    	return findByObject(id, "id", "findByPurchaseOrderId");
    }
    
    public List<PurchaseOrderDetailDto> findByProductId(Integer id) {
    	return findByObject(id, "id", "findByProductId");
    }
    
    public List<PurchaseOrderDetailDto> findByInventoryTransactionId(Integer id) {
    	return findByObject(id, "id", "findByInventoryTransactionId");
    }

    public List<PurchaseOrderDetailDto> findByQuantityBetweenOrderByQuantityAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByQuantityBetweenOrderByQuantityAsc");
    }
    
    public List<PurchaseOrderDetailDto> findByUnitCostBetweenOrderByUnitCostAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByUnitCostBetweenOrderByUnitCostAsc");
    }

    public List<PurchaseOrderDetailDto> findByDateReceivedBetweenOrderByDateReceivedAsc(LocalDateTime start, LocalDateTime end) {
    	return findWithTwoParameters(start, "start", end, "end", "findByDateReceivedBetweenOrderByDateReceivedAsc");
    }
    
    public List<PurchaseOrderDetailDto> findByPostedToInventory(Boolean postedToInventory) {
    	return findByObject(postedToInventory, "postedToInventory", "findByPostedToInventory");
    }
    
    private List<PurchaseOrderDetailDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/purchaseOrderDetail/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(PurchaseOrderDetailDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
    
    private List<PurchaseOrderDetailDto> findWithTwoParameters(Object param1, String paramName1, 
			 											 	   Object param2, String paramName2, String path) {
			return restClient.get()
			.uri(uriBuilder -> uriBuilder.path("/api/purchaseOrderDetail/search/" + path)
			.queryParam(paramName1, param1)
			.queryParam(paramName2, param2)
			.build())
			.retrieve()
			.body(new org.springframework.core.ParameterizedTypeReference<List<PurchaseOrderDetailDto>>() {});    	
    }
    
}
