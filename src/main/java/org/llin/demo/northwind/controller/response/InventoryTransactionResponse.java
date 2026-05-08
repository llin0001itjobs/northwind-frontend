package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.InventoryTransaction;

public class InventoryTransactionResponse extends BaseResponse {

	InventoryTransaction[] inventoryTransactions;

	public InventoryTransaction[] getInventoryTransactions() {
		return inventoryTransactions;
	}

	public void setInventoryTransactions(InventoryTransaction[] inventoryTransactions) {
		this.inventoryTransactions = inventoryTransactions;
	}

	@Override
	public  EntityObject[] getResponse() {
		return inventoryTransactions;
	}
	
}
