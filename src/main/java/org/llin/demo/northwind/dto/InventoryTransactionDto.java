package org.llin.demo.northwind.dto;

import java.time.LocalDate;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record InventoryTransactionDto(
		int id,
		
		ProductDto product,
		InventoryTransactionTypeDto inventoryTransactionType,

		LocalDate transactionCreatedDate,
		LocalDate transactionModifiedDate,

		double quantity,
		String comments,

		CustomerOrderDto customerOrder,
		PurchaseOrderDto purchaseOrder,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links
) {}
