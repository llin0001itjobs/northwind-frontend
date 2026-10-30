package org.llin.demo.northwind.dto;

import java.time.LocalDate;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record PurchaseOrderDetailDto(int id,

		InventoryTransactionDto inventoryTransaction, 
		ProductDto product, 
		PurchaseOrderDto purchaseOrder,

		int quantity, 
		double unitCost, 
		LocalDate dateReceived, 
		boolean postedToInventory,
		
        @JsonProperty("_links")
        @JsonDeserialize(using = LinksDeserializer.class) 
        Links links

) {}
