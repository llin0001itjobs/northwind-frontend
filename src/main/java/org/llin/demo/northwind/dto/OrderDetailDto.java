package org.llin.demo.northwind.dto;

import java.time.LocalDate;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record OrderDetailDto(
		int id,

		CustomerOrderDto customerOrder,
		ProductDto product,
		OrderStatusDto orderStatus,
		PurchaseOrderDto purchaseOrder,
		InventoryTransactionTypeDto inventoryTransaction,

		double quantity,
		double unitPrice,
		double discount,
		LocalDate dateAllocated,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links
		
) {}
