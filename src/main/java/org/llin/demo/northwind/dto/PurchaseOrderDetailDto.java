package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record PurchaseOrderDetailDto(
		int id,

		PurchaseOrderDto purchaseOrder,
		ProductDto product,
		InventoryTransactionTypeDto inventoryTransaction,

		int quantity,
		double unitCost,
		LocalDateTime dateReceived,
		boolean postedToInventory,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links linkss
) {}
