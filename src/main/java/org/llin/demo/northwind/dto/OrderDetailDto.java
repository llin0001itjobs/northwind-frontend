package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

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
		LocalDateTime dateAllocated
		
) {}
