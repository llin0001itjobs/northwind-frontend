package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PaymentTypeService {

    private final RestClient restClient;

    @Autowired
    public PaymentTypeService(@Qualifier("northwindDataClient") RestClient restClient) {
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
        @com.fasterxml.jackson.annotation.JsonProperty("paymentTypes")
        public List<PaymentTypeDto> PaymentType;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /paymentType  (returns all PaymentTypes)
     */
    public List<PaymentTypeDto> findAll() {
    	EmbeddedPaymentTypes response = restClient.get()
                .uri("paymentType")
                .retrieve()
                .body(EmbeddedPaymentTypes.class);

        return response != null 
                && response.PaymentTypes != null 
                && response.PaymentTypes.PaymentType != null
                    ? response.PaymentTypes.PaymentType
                    : List.of();
    }
    
    
    public List<PaymentTypeDto> findById(Integer id) {
		if (id == null)
			return List.of();

		List<PaymentTypeDto> list = new ArrayList<>();

		PaymentTypeDto dto = restClient.get().uri("orderStatus/{id}", id).retrieve().body(PaymentTypeDto.class);

		if (dto != null)
			list.add(dto);

		return list;
    }    
 
}