package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

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

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<CustomerOrderDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/customerOrder/{id}", id)
                        .retrieve()
                        .body(CustomerOrderDto.class)
        );
    }

    /**
     * GET /CustomerOrder  (returns all CustomerOrders)
     */
    public List<CustomerOrderDto> findAll() {
        EmbeddedCustomerOrders response = restClient.get()
                .uri("/customerOrder")
                .retrieve()
                .body(EmbeddedCustomerOrders.class);

        return response != null 
                && response.CustomerOrders != null 
                && response.CustomerOrders.CustomerOrder != null
                    ? response.CustomerOrders.CustomerOrder
                    : List.of();
    }

    public CustomerOrderDto create(CustomerOrderDto CustomerOrderDto) {
        return restClient.post()
                .uri("/customerOrder")
                .body(CustomerOrderDto)
                .retrieve()
                .body(CustomerOrderDto.class);
    }

    public CustomerOrderDto update(Integer id, CustomerOrderDto CustomerOrderDto) {
        return restClient.put()
                .uri("/customerOrder/{id}", id)
                .body(CustomerOrderDto)
                .retrieve()
                .body(CustomerOrderDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/customerOrder/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}