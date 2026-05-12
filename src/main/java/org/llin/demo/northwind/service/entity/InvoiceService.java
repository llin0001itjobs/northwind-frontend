package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final RestClient restClient;

    @Autowired
    public InvoiceService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedInvoices {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public InvoiceList Invoices;
    }

    private static class InvoiceList {
        @com.fasterxml.jackson.annotation.JsonProperty("invoice")
        public List<InvoiceDto> Invoice;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<InvoiceDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/invoice/{id}", id)
                        .retrieve()
                        .body(InvoiceDto.class)
        );
    }

    /**
     * GET /Invoice  (returns all Invoices)
     */
    public List<InvoiceDto> findAll() {
        EmbeddedInvoices response = restClient.get()
                .uri("/invoice")
                .retrieve()
                .body(EmbeddedInvoices.class);

        return response != null 
                && response.Invoices != null 
                && response.Invoices.Invoice != null
                    ? response.Invoices.Invoice
                    : List.of();
    }

    public InvoiceDto create(InvoiceDto InvoiceDto) {
        return restClient.post()
                .uri("/invoice")
                .body(InvoiceDto)
                .retrieve()
                .body(InvoiceDto.class);
    }

    public InvoiceDto update(Integer id, InvoiceDto InvoiceDto) {
        return restClient.put()
                .uri("/invoice/{id}", id)
                .body(InvoiceDto)
                .retrieve()
                .body(InvoiceDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/invoice/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}