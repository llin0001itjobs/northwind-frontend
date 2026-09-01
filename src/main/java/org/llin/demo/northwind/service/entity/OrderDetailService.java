package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.OrderDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class OrderDetailService {

    private final RestClient restClient;

    @Autowired
    public OrderDetailService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedOrderDetails {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public OrderDetailList OrderDetails;
    }

    private static class OrderDetailList {
        @com.fasterxml.jackson.annotation.JsonProperty("orderDetail")
        public List<OrderDetailDto> OrderDetail;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<OrderDetailDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("orderDetail/{id}", id)
                        .retrieve()
                        .body(OrderDetailDto.class)
        );
    }

    /**
     * GET /orderDetail  (returns all OrderDetails)
     */
    public List<OrderDetailDto> findAll() {
        EmbeddedOrderDetails response = restClient.get()
                .uri("orderDetail")
                .retrieve()
                .body(EmbeddedOrderDetails.class);

        return response != null 
                && response.OrderDetails != null 
                && response.OrderDetails.OrderDetail != null
                    ? response.OrderDetails.OrderDetail
                    : List.of();
    }

    public OrderDetailDto create(OrderDetailDto OrderDetailDto) {
        return restClient.post()
                .uri("orderDetail")
                .body(OrderDetailDto)
                .retrieve()
                .body(OrderDetailDto.class);
    }

    public OrderDetailDto update(Integer id, OrderDetailDto OrderDetailDto) {
        return restClient.put()
                .uri("orderDetail/{id}", id)
                .body(OrderDetailDto)
                .retrieve()
                .body(OrderDetailDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("orderDetail/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public List<OrderDetailDto> findByCustomerOrderId(Integer id) {
    	return findByObject(id, "id", "findByCustomerOrderId");
    }
    
    public List<OrderDetailDto> findByProductId(Integer id) {
    	return findByObject(id, "id", "findByProductId");
    }
    
    public List<OrderDetailDto> findByOrderStatusId(Integer id) {
    	return findByObject(id, "id", "findByOrderStatusId");
    }
    
    public List<OrderDetailDto> findByPurchaseOrderId(Integer id) {
    	return findByObject(id, "id", "findByPurchaseOrderId");
    }
    
    public List<OrderDetailDto> findByInventoryTransactionId(Integer id) {
    	return findByObject(id, "id", "findByInventoryTransactionId");	
    }

    // Numeric ranges
    public List<OrderDetailDto> findByQuantityBetweenOrderByQuantityAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByQuantityBetweenOrderByQuantityAsc");
    }
    
    public List<OrderDetailDto> findByUnitPriceBetweenOrderByUnitPriceAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByUnitPriceBetweenOrderByUnitPriceAsc");
    }
    
    public List<OrderDetailDto> findByDiscountBetweenOrderByDiscountAsc(Double min, Double max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByDiscountBetweenOrderByDiscountAsc");
    }

    public List<OrderDetailDto> findByDateAllocatedBetweenOrderByDateAllocatedAsc(LocalDateTime start, LocalDateTime end) {
    	return findWithTwoParameters(start, "start", end, "end", "findByDateAllocatedBetweenOrderByDateAllocatedAsc");
    }
    
	private List<OrderDetailDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get().uri(uriBuilder -> uriBuilder
					.path("orderDetail/search/{method}")
					.queryParam(paramName, value)
					.build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<OrderDetailDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}
    
	private List<OrderDetailDto> findWithTwoParameters(Object param1, String paramName1, 
													   Object param2, String paramName2,
													   				  String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder
							.path("orderDetail/search/" + path)
							.queryParam(paramName1, param1)
							.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<OrderDetailDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}       
    
}