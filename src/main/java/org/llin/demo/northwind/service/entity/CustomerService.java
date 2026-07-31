package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
                        .uri("/api/customer/{id}", id)
                        .retrieve()
                        .body(CustomerDto.class)
        );
    }

    /**
     * GET /customer  (returns all customers)
     */
    public List<CustomerDto> findAll() {
        EmbeddedCustomers response = restClient.get()
                .uri("/api/customer")
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
                .uri("/api/customer")
                .body(customerDto)
                .retrieve()
                .body(CustomerDto.class);
    }

    public CustomerDto update(Integer id, CustomerDto customerDto) {
        return restClient.put()
                .uri("/api/customer/{id}", id)
                .body(customerDto)
                .retrieve()
                .body(CustomerDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/customer/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public List<CustomerDto> findAllByLastName(String lastName) {
		return findByObject(lastName,"lastName","findAllByLastName");
	}
	
    public List<CustomerDto> findByJobTitle(String jobTitle) {
		return findByObject(jobTitle,"jobTitle","findByJobTitle");
	}
	
    public List<CustomerDto> findByEmailAddress(String emailAddress) {
		return findByObject(emailAddress,"emailAddress","findByEmailAddress"); 
	}
	
    public List<CustomerDto> findByLastNameContaining(String lastName) {        // LIKE '%value%'
		return findByObject(lastName,"lastName","findByLastNameContaining");
	}
	
    public List<CustomerDto> findByFirstNameContaining(String firstName) {
		return findByObject(firstName,"firstName","findByFirstNameContaining");
	}
	
    public List<CustomerDto> findByJobTitleContaining(String jobTitle) {
		return findByObject(jobTitle,"jobTitle","findByJobTitleContaining");
	}
	
    private List<CustomerDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/customer/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(CustomerDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
}