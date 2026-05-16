package org.llin.demo.northwind.service.entity;

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
				restClient.get().uri("/purchaseOrderDetail/{id}", id).retrieve().body(PurchaseOrderDetailDto.class));
	}

	/**
	 * GET /purchaseOrderDetail (returns all PurchaseOrderDetails)
	 */
	public List<PurchaseOrderDetailDto> findAll() {
		EmbeddedPurchaseOrderDetails response = restClient.get().uri("/purchaseOrderDetail").retrieve()
				.body(EmbeddedPurchaseOrderDetails.class);

		return response != null && response.PurchaseOrderDetails != null
				&& response.PurchaseOrderDetails.PurchaseOrderDetail != null
						? response.PurchaseOrderDetails.PurchaseOrderDetail
						: List.of();
	}

	public PurchaseOrderDetailDto create(PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.post().uri("/purchaseOrderDetail").body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public PurchaseOrderDetailDto update(Integer id, PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.put().uri("/purchaseOrderDetail/{id}", id).body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("/purchaseOrderDetail/{id}", id).retrieve().toBodilessEntity();
	}
	
	public List<LabelValueLongDto> shippingFeePerMonth() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/purchaseOrderDetail/analytics/shipping-fee-per-month").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }
	
	public List<LabelDoubleValueLongDto> quantityPerUnitCost() {
		EmbeddedLabelDoubleValueLongs response = restClient.get()
    			.uri("/purchaseOrderDetail/analytics/quantity-per-unit-cost").retrieve()
    			.body(EmbeddedLabelDoubleValueLongs.class);
    	return response != null && response.LabelDoubleValueLongs != null && response.LabelDoubleValueLongs.LabelDoubleValueLong != null
				? response.LabelDoubleValueLongs.LabelDoubleValueLong
				: List.of();		
    }
	
	
}
