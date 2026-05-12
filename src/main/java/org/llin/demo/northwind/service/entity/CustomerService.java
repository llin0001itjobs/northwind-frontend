package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final RestClient restClient;

    @Autowired
    public CustomerService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedCustomers {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public CustomerList customers;
    }

    private static class CustomerList {
        @com.fasterxml.jackson.annotation.JsonProperty("customer")
        public List<CustomerDto> customer;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<CustomerDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/customer/{id}", id)
                        .retrieve()
                        .body(CustomerDto.class)
        );
    }

    /**
     * GET /customer  (returns all customers)
     */
    public List<CustomerDto> findAll() {
        EmbeddedCustomers response = restClient.get()
                .uri("/customer")
                .retrieve()
                .body(EmbeddedCustomers.class);

        return response != null 
                && response.customers != null 
                && response.customers.customer != null
                    ? response.customers.customer
                    : List.of();
    }

    public CustomerDto create(CustomerDto customerDto) {
        return restClient.post()
                .uri("/customer")
                .body(customerDto)
                .retrieve()
                .body(CustomerDto.class);
    }

    public CustomerDto update(Integer id, CustomerDto customerDto) {
        return restClient.put()
                .uri("/customer/{id}", id)
                .body(customerDto)
                .retrieve()
                .body(CustomerDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/customer/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}