package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
                        .uri("/api/invoice/{id}", id)
                        .retrieve()
                        .body(InvoiceDto.class)
        );
    }

    /**
     * GET /Invoice  (returns all Invoices)
     */
    public List<InvoiceDto> findAll() {
        EmbeddedInvoices response = restClient.get()
                .uri("/api/invoice")
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
                .uri("/api/invoice")
                .body(InvoiceDto)
                .retrieve()
                .body(InvoiceDto.class);
    }

    public InvoiceDto update(Integer id, InvoiceDto InvoiceDto) {
        return restClient.put()
                .uri("/api/invoice/{id}", id)
                .body(InvoiceDto)
                .retrieve()
                .body(InvoiceDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/invoice/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    // === Your original range queries (cleaned up) ===
    public List<InvoiceDto> findByDueDateBetweenOrderByDueDateAsc(LocalDateTime dueDate1, LocalDateTime dueDate2) {
    	return findWithTwoParameters(dueDate1, "dueDate1", dueDate2, "dueDate2",
    			"findByDueDateBetweenOrderByDueDateAsc");    	    	
    }

    public List<InvoiceDto> findByInvoiceDateBetweenOrderByInvoiceDateAsc(LocalDateTime invoiceDate1, LocalDateTime invoiceDate2) {
    	return findWithTwoParameters(invoiceDate1, "invoiceDate1", invoiceDate2, "invoiceDate2",
    			"findByInvoiceDateBetweenOrderByInvoiceDateAsc");    	
    }

    public List<InvoiceDto> findByAmountDueBetweenOrderByAmountDueAsc(BigDecimal amountDue1, BigDecimal amountDue2) {
    	return findWithTwoParameters(amountDue1, "amountDue1", amountDue2, "amountDue2",
    			"findByAmountDueBetweenOrderByAmountDueAsc");     	
    }

    public List<InvoiceDto> findByShippingBetweenOrderByShippingAsc(BigDecimal shipping1, BigDecimal shipping2) {
    	return findWithTwoParameters(shipping1, "shipping1", shipping2, "shipping2",
    			"findByShippingBetweenOrderByShippingAsc");   
    }

    public List<InvoiceDto> findByTaxBetweenOrderByTaxAsc(BigDecimal tax1, BigDecimal tax2) {
    	return findWithTwoParameters(tax1, "tax1", tax2, "tax2",
    			"findByTaxBetweenOrderByTaxAsc");    	
    }

    // === Very useful additional filters ===

    // By linked order (most important for invoices!)
    public List<InvoiceDto> findByCustomerOrderId(Integer id) {
    	return findByObject(id, "id", "findByCustomerOrderId");
    }

    // Single-date helpers
    public List<InvoiceDto> findByInvoiceDateAfterOrderByInvoiceDateAsc(LocalDateTime date) {
    	return findByObject(date, "date", "findByInvoiceDateAfterOrderByInvoiceDateAsc"); 
    }
    
    public List<InvoiceDto> findByDueDateBeforeOrderByDueDateAsc(LocalDateTime date) {
    	return findByObject(date, "date", "findByDueDateBeforeOrderByDueDateAsc");
    }

    // Overdue invoices (very common need)
    public List<InvoiceDto> findByDueDateBeforeAndAmountDueGreaterThanOrderByDueDateAsc(
            LocalDateTime date, BigDecimal amountDue) {
    	return findWithTwoParameters(date, "date", amountDue, "amountDue",
    			"findByDueDateBeforeAndAmountDueGreaterThanOrderByDueDateAsc");
    }

    // Combined date + amount (great for reporting)
    public List<InvoiceDto> findByInvoiceDateBetweenAndAmountDueGreaterThanOrderByInvoiceDateAsc(
            LocalDateTime startDate, LocalDateTime endDate, BigDecimal minAmount) {
    	return findWithThreeParameters(startDate, "startDate", endDate, "endDate", minAmount, "minAmount",
    			"findByInvoiceDateBetweenAndAmountDueGreaterThanOrderByInvoiceDateAsc");
    }

    // Search by amount due greater/less than
    public List<InvoiceDto> findByAmountDueGreaterThanOrderByAmountDueDesc(BigDecimal amount) {
    	return findByObject(amount, "amount", "findByAmountDueGreaterThanOrderByAmountDueDesc");
    }
    
    public List<InvoiceDto> findByAmountDueLessThanOrderByAmountDueAsc(BigDecimal amount) {
    	return findByObject(amount, "amount", "findByAmountDueLessThanOrderByAmountDueAsc");
    }
    
    private List<InvoiceDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/invoice/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(InvoiceDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
    
    private List<InvoiceDto> findWithTwoParameters(Object param1, String paramName1, 
			 											 Object param2, String paramName2, String path) {
			return restClient.get()
			.uri(uriBuilder -> uriBuilder.path("/api/invoice/search/" + path)
			.queryParam(paramName1, param1)
			.queryParam(paramName2, param2)
			.build())
			.retrieve()
			.body(new org.springframework.core.ParameterizedTypeReference<List<InvoiceDto>>() {});    	
    }    
    
    private List<InvoiceDto> findWithThreeParameters(Object param1, String paramName1, 
		       Object param2, String paramName2, 
			   Object param3, String paramName3, String path) {
		return restClient.get()
		.uri(uriBuilder -> uriBuilder.path("/api/invoice/search/" + path)
		.queryParam(paramName1, param1)
		.queryParam(paramName2, param2)
		.queryParam(paramName3, param3)
		.build())
		.retrieve()
		.body(new org.springframework.core.ParameterizedTypeReference<List<InvoiceDto>>() {});    	
    }
    
}