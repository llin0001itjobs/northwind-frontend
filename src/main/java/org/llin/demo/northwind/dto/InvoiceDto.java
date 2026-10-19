package org.llin.demo.northwind.dto;

import java.time.LocalDate;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record InvoiceDto(
		int id,
		
		CustomerOrderDto customerOrder,
		
		LocalDate invoiceDate,
		LocalDate dueDate,

		double tax,
		double shipping,		
		double amountDue,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links

) {}
