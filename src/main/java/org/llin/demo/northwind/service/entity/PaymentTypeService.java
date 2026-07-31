package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PaymentTypeService {

    private final RestClient restClient;

    @Autowired
    public PaymentTypeService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedPaymentTypes {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public PaymentTypeList PaymentTypes;
    }

    private static class PaymentTypeList {
        @com.fasterxml.jackson.annotation.JsonProperty("paymentType")
        public List<PaymentTypeDto> PaymentType;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /Role  (returns all Roles)
     */
    public List<PaymentTypeDto> findAll() {
    	EmbeddedPaymentTypes response = restClient.get()
                .uri("/api/paymentType")
                .retrieve()
                .body(EmbeddedPaymentTypes.class);

        return response != null 
                && response.PaymentTypes != null 
                && response.PaymentTypes.PaymentType != null
                    ? response.PaymentTypes.PaymentType
                    : List.of();
    }
    
    
    public Optional<PaymentTypeDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/paymentType/{id}", id)
                        .retrieve()
                        .body(PaymentTypeDto.class)
        );
    }    
 
}