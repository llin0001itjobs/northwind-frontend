package org.llin.demo.northwind.service;

import org.llin.demo.northwind.dto.OrderDetailDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final RestClient restClient;

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
                        .uri("/orderDetail/{id}", id)
                        .retrieve()
                        .body(OrderDetailDto.class)
        );
    }

    /**
     * GET /OrderDetail  (returns all OrderDetails)
     */
    public List<OrderDetailDto> findAll() {
        EmbeddedOrderDetails response = restClient.get()
                .uri("/orderDetail")
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
                .uri("/orderDetail")
                .body(OrderDetailDto)
                .retrieve()
                .body(OrderDetailDto.class);
    }

    public OrderDetailDto update(Integer id, OrderDetailDto OrderDetailDto) {
        return restClient.put()
                .uri("/orderDetail/{id}", id)
                .body(OrderDetailDto)
                .retrieve()
                .body(OrderDetailDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/orderDetail/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}