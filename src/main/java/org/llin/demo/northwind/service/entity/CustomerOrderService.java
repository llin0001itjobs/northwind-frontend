package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.dto.LabelIntValueDoubleDto;
import org.llin.demo.northwind.dto.LabelIntValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CustomerOrderService {

	private final RestClient restClient;

	@Autowired
	public CustomerOrderService(RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedCustomerOrders {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public CustomerOrderList CustomerOrders;
	}

	private static class CustomerOrderList {
		@com.fasterxml.jackson.annotation.JsonProperty("customerOrder")
		public List<CustomerOrderDto> CustomerOrder;
	}

	private static class EmbeddedLabelValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongList LabelValueLongs;
	}

	private static class LabelValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLong")
		public List<LabelValueLongDto> LabelValueLong;
	}

	private static class EmbeddedLabelIntValueDoubles {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelIntValueDoubleList LabelIntValueDoubles;
	}

	private static class LabelIntValueDoubleList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelIntValueDouble")
		public List<LabelIntValueDoubleDto> LabelIntValueDouble;
	}
	
	private static class EmbeddedLabelIntValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelIntValueLongList LabelIntValueLongs;
	}

	private static class LabelIntValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelIntValueLong")
		public List<LabelIntValueLongDto> LabelIntValueLong;
	}
	
	// ==================================================================
	// Public methods
	// ==================================================================

	public Optional<CustomerOrderDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional
				.ofNullable(restClient.get().uri("/customerOrder/{id}", id).retrieve().body(CustomerOrderDto.class));
	}

	/**
	 * GET /CustomerOrder (returns all CustomerOrders)
	 */
	public List<CustomerOrderDto> findAll() {
		EmbeddedCustomerOrders response = restClient.get().uri("/customerOrder").retrieve()
				.body(EmbeddedCustomerOrders.class);

		return response != null && response.CustomerOrders != null && response.CustomerOrders.CustomerOrder != null
				? response.CustomerOrders.CustomerOrder
				: List.of();
	}

	public CustomerOrderDto create(CustomerOrderDto CustomerOrderDto) {
		return restClient.post().uri("/customerOrder").body(CustomerOrderDto).retrieve().body(CustomerOrderDto.class);
	}

	public CustomerOrderDto update(Integer id, CustomerOrderDto CustomerOrderDto) {
		return restClient.put().uri("/customerOrder/{id}", id).body(CustomerOrderDto).retrieve()
				.body(CustomerOrderDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("/customerOrder/{id}", id).retrieve().toBodilessEntity();
	}

	public List<LabelValueLongDto> feeRangePerCount() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/customerOrder/analytics/fee-range-count").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }

	public List<LabelIntValueDoubleDto> shippingFeePerMonth() {
		EmbeddedLabelIntValueDoubles response = restClient.get()
    			.uri("/customerOrder/analytics/shipping-fee-month").retrieve()
    			.body(EmbeddedLabelIntValueDoubles.class);
    	return response != null && response.LabelIntValueDoubles != null && response.LabelIntValueDoubles.LabelIntValueDouble != null
				? response.LabelIntValueDoubles.LabelIntValueDouble
				: List.of();		
    }
	
	public List<LabelIntValueLongDto> orderCountPerMonth() {
		EmbeddedLabelIntValueLongs response = restClient.get()
    			.uri("/customerOrder/analytics/order-count-month").retrieve()
    			.body(EmbeddedLabelIntValueLongs.class);
    	return response != null && response.LabelIntValueLongs != null && response.LabelIntValueLongs.LabelIntValueLong != null
				? response.LabelIntValueLongs.LabelIntValueLong
				: List.of();		
    }
	
	public List<LabelValueLongDto> ordersByStatus() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/customerOrder/analytics/by-status").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }
    
    
}