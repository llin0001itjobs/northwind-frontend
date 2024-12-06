package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
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
	public  BaseObject[] getResponse() {
		return inventoryTransactions;
	}
	
}
