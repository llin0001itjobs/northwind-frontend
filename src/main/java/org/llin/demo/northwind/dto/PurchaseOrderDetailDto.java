package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

public record PurchaseOrderDetailDto(
		int id,

		PurchaseOrderDto purchaseOrder,
		ProductDto product,
		InventoryTransactionTypeDto inventoryTransaction,

		int quantity,
		double unitCost,
		LocalDateTime dateReceived,
		boolean postedToInventory
) {}
