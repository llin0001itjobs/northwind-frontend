package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record InventoryTransactionDto(
		int id,
		
		ProductDto product,
		InventoryTransactionTypeDto inventoryTransactionType,

		LocalDateTime transactionCreatedDate,
		LocalDateTime transactionModifiedDate,

		double quantity,
		String comments,

		CustomerOrderDto customerOrder,
		PurchaseOrderDto purchaseOrder,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links
) {}
