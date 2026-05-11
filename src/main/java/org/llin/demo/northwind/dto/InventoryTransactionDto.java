package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

public record InventoryTransactionDto(
		int id,
		
		ProductDto product,
		InventoryTransactionTypeDto inventoryTransactionType,

		LocalDateTime transactionCreatedDate,
		LocalDateTime transactionModifiedDate,

		double quantity,
		String comments,

		CustomerOrderDto customerOrder,
		PurchaseOrderDto purchaseOrder
) {}
