package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.dto.LabelIntValueDoubleDto;
import org.llin.demo.northwind.dto.LabelIntValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

	public List<CustomerOrderDto> findAll() {
		EmbeddedCustomerOrders response = restClient.get().uri("/api/customerOrder").retrieve()
				.body(EmbeddedCustomerOrders.class);

		return response != null && response.CustomerOrders != null && response.CustomerOrders.CustomerOrder != null
				? response.CustomerOrders.CustomerOrder
				: List.of();
	}

	public Optional<CustomerOrderDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional
				.ofNullable(restClient.get()
							.uri("/api/customerOrder/{id}", id)
							.retrieve()
							.body(CustomerOrderDto.class));
	}
	
	public CustomerOrderDto create(CustomerOrderDto CustomerOrderDto) {
		return restClient.post()
				.uri("/api/customerOrder")
				.body(CustomerOrderDto)
				.retrieve()
				.body(CustomerOrderDto.class);
	}

	public CustomerOrderDto update(Integer id, CustomerOrderDto CustomerOrderDto) {
		return restClient.put()
				.uri("/api/customerOrder/{id}", id)
				.body(CustomerOrderDto).retrieve()
				.body(CustomerOrderDto.class);
	}

	public void deleteById(Integer id) {
			   restClient.delete()
					.uri("/api/customerOrder/{id}", id)
					.retrieve()
					.toBodilessEntity();
	}

	public List<LabelValueLongDto> feeRangePerCount() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/api/customerOrder/search/fee-range-count").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }

	public List<LabelIntValueDoubleDto> shippingFeePerMonth() {
		EmbeddedLabelIntValueDoubles response = restClient.get()
    			.uri("/api/customerOrder/search/shipping-fee-month").retrieve()
    			.body(EmbeddedLabelIntValueDoubles.class);
    	return response != null && response.LabelIntValueDoubles != null && response.LabelIntValueDoubles.LabelIntValueDouble != null
				? response.LabelIntValueDoubles.LabelIntValueDouble
				: List.of();		
    }
	
	public List<LabelIntValueLongDto> orderCountPerMonth() {
		EmbeddedLabelIntValueLongs response = restClient.get()
    			.uri("/api/customerOrder/search/order-count-month").retrieve()
    			.body(EmbeddedLabelIntValueLongs.class);
    	return response != null && response.LabelIntValueLongs != null && response.LabelIntValueLongs.LabelIntValueLong != null
				? response.LabelIntValueLongs.LabelIntValueLong
				: List.of();		
    }
	
	public List<LabelValueLongDto> ordersByStatus() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/api/customerOrder/search/by-status").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }
    
	public List<CustomerOrderDto> findByCustomerId(Integer id) {
		return findByObject(id, "id", "findByCustomerId");
	}

	// Optional: sort by most recent orders
	public List<CustomerOrderDto> findByCustomerIdOrderByOrderDateDesc(Integer customerId) {
    	return findByObject(customerId, "customerId", "findByCustomerIdOrderByOrderDateDesc");
    }
    
	public List<CustomerOrderDto> findByCustomerIdAndShipCityContaining(
            Integer customerId, String shipCity) {
    	return findWithTwoParameters(customerId, "customerId", shipCity, "shipCity", "findByCustomerIdAndShipCityContaining");
    }
    
	public List<CustomerOrderDto> findByEmployeeId(Integer id) {
		return findByObject(id, "id", "findByEmployeeId");
	}

	public List<CustomerOrderDto> findByShipperId(Integer id) {
		return findByObject(id,"id", "findByShipperId");
	}
	
	public List<CustomerOrderDto> findByOrderStatusId(Integer id) {
		return findByObject(id,"id", "findByOrderStatusId");
	}

	public List<CustomerOrderDto> findByOrderTaxStatusId(Integer id) {
		return findByObject(id,"id", "findByOrderTaxStatusId");				
	}
	    
	public List<CustomerOrderDto> findByShipName(String shipName) {    	  
		return findByObject(shipName, "shipName", "findByShipName");
    }
	
	public List<CustomerOrderDto> findByShipNameContaining(String shipName) {    	  
		return findByObject(shipName, "shipName", "findByShipNameContaining");
    }
	
	public List<CustomerOrderDto> findByShipCity(String shipCity) {    	  
		return findByObject(shipCity, "shipCity", "findByShipCity");
    }
	
	public List<CustomerOrderDto> findByShipCityContaining(String shipCity) {    	  
		return findByObject(shipCity, "shipCity", "findByShipCityContaining");
    }
	
	public List<CustomerOrderDto> findByShipStateProvince(String shipStateProvince) {    	  
		return findByObject(shipStateProvince, "shipStateProvince", "findByShipStateProvince");
    }

	public List<CustomerOrderDto> findByShipStateProvinceContaining(String shipStateProvince) {    	  
		return findByObject(shipStateProvince, "shipStateProvince", "findByShipStateProvinceContaining");
    }
	
	public List<CustomerOrderDto> findByShipCountryRegion(String shipCountryRegion) {    	  
		   return findByObject(shipCountryRegion, "shipCountryRegion", "findByShipCountryRegion");
    }
	
	public List<CustomerOrderDto> findByShipCountryRegionContaining(String shipCountryRegion) {    	  
		   return findByObject(shipCountryRegion, "shipCountryRegion", "findByShipCountryRegionContaining");
	}
	
	public List<CustomerOrderDto> findByNotesContaining(String notes) {    	  
		   return findByObject(notes, "notes", "findByNotesContaining");
	}
	
	public List<CustomerOrderDto> findByPaymentType(String paymentType) {    	  
		   return findByObject(paymentType, "paymentType", "findByPaymentType");
	} 
        
	public List<CustomerOrderDto> findByOrderDateBetweenOrderByOrderDateAsc(
            LocalDateTime startDate, LocalDateTime endDate) {
    	return findWithTwoParameters(startDate, "startDate", endDate, "endDate", "findByOrderDateBetweenOrderByOrderDateAsc");
    }

	public List<CustomerOrderDto> findByShippedDateBetweenOrderByShippedDateAsc(
            LocalDateTime startDate, LocalDateTime endDate) {
    	return findWithTwoParameters(startDate, "startDate", endDate, "endDate", "findByShippedDateBetweenOrderByShippedDateAsc");
    }

	public List<CustomerOrderDto> findByPaidDateBetweenOrderByPaidDateAsc(
            LocalDateTime startDate, LocalDateTime endDate) {
    	return findWithTwoParameters(startDate, "startDate", endDate, "endDate", "findByPaidDateBetweenOrderByPaidDateAsc");
    }
        
    // Single-date helpers
	public List<CustomerOrderDto> findByOrderDateAfterOrderByOrderDateAsc(LocalDateTime date) {
    	return findByObject(date,"date","findByOrderDateAfterOrderByOrderDateAsc");
    }
    
	public List<CustomerOrderDto> findByShippedDateIsNull() { // not yet shipped
    	return findByNull("findByShippedDateIsNull");
    }
	
	public List<CustomerOrderDto> findByPaidDateIsNull() {	   // not yet paid
    	return findByNull("findByPaidDateIsNull");
    }
    
    // === Money / numeric ranges ===
	public List<CustomerOrderDto> findByShippingFeeBetweenOrderByShippingFeeAsc(
            BigDecimal minFee, BigDecimal maxFee) {
    	return findWithTwoParameters(minFee, "minFee", maxFee, "maxFee", "findByShippingFeeBetweenOrderByShippingFeeAsc");
    }

	public List<CustomerOrderDto> findByTaxesBetweenOrderByTaxesAsc(
            BigDecimal minTax, BigDecimal maxTax) {
    	return findWithTwoParameters(minTax, "minTax", maxTax, "maxTax", "findByTaxesBetweenOrderByTaxesAsc"); 
    }

	public List<CustomerOrderDto> findByTaxRateBetweenOrderByTaxRateAsc(
    		BigDecimal minRate, BigDecimal maxRate) {
    	return findWithTwoParameters(minRate, "minRate", maxRate, "maxRate", "findByTaxRateBetweenOrderByTaxRateAsc");
    }

	public List<CustomerOrderDto> findByCustomerIdAndOrderStatusId(Integer customerId, Integer statusId) {
    	return findWithTwoParameters(customerId, "customerId", statusId, "statusId", "findByCustomerIdAndOrderStatusId");
    }
    
	public List<CustomerOrderDto> findByOrderDateBetweenAndOrderStatusIdOrderByOrderDateAsc(
            LocalDateTime startDate, LocalDateTime endDate, Integer statusId) {
    	return findWithThreeParameters(startDate,"startDate",endDate, "endDate", statusId, "statusId",
    								   "findByOrderDateBetweenAndOrderStatusIdOrderByOrderDateAsc");
    }
    
	public List<CustomerOrderDto> findByOrderStatusIdAndPaidDateIsNull(Integer statusId) { // pending payment
    	return findByObjectAndNullField(statusId, "statusId","findByOrderStatusIdAndPaidDateIsNull");    	
    }
        
    private List<CustomerOrderDto> findByNull(String path) {

        // Basic safety check for the path variable
        if (path == null || path.isEmpty()) {
            return Collections.emptyList();
        }

        List<CustomerOrderDto> response = restClient.get()
                .uri("/api/customerOrder/search/" + path)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {});

        return response != null ? response : Collections.emptyList();
    }
    
    private List<CustomerOrderDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/customerOrder/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(CustomerOrderDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
    
 // 2. The generic helper method
    private List<CustomerOrderDto> findByObjectAndNullField(Object param, String paramLabel, String path) {
        // Return empty early if the status ID is missing
        if (param == null) {
            return Collections.emptyList();
        }

        List<CustomerOrderDto> response = restClient.get()
                .uri("/api/customerOrder/search/" + path + "?" + paramLabel + "={" + paramLabel + "}", param)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {});

        return response != null ? response : Collections.emptyList();
    }
    
    private List<CustomerOrderDto> findWithThreeParameters(Object param1, String paramName1, 
    												       Object param2, String paramName2, 
    													   Object param3, String paramName3, String path) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/customerOrder/search/" + path)
                        .queryParam(paramName1, param1)
                        .queryParam(paramName2, param2)
                        .queryParam(paramName3, param3)
                        .build())
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<CustomerOrderDto>>() {});    	
    }
    
    private List<CustomerOrderDto> findWithTwoParameters(Object param1, String paramName1, 
    													 Object param2, String paramName2, String path) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/customerOrder/search/" + path)
                        .queryParam(paramName1, param1)
                        .queryParam(paramName2, param2)
                        .build())
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<CustomerOrderDto>>() {});    	
    }
    
    
}