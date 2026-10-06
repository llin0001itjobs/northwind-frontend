package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record PurchaseOrderDto(
		int id,

		EmployeeDto approvedBy,
		EmployeeDto createdBy,
		EmployeeDto submittedBy,
		SupplierDto supplier,
		OrderStatusDto orderStatus,

		LocalDateTime approvedDate,
		LocalDateTime submittedDate,
		LocalDateTime creationDate,
		LocalDateTime expectedDate,

		double shippingFee,
		double taxes,
		LocalDateTime paymentDate,
		double paymentAmount,
		String paymentMethod,

		String notes,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links
) {}
