package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.InventoryTransactionType;

public class InventoryTransactionTypeResponse extends BaseResponse {

	InventoryTransactionType[] inventoryTransactionTypes;

	public InventoryTransactionType[] getinventoryTransactionTypes() {
		return inventoryTransactionTypes;
	}

	public void setinventoryTransactionTypes(InventoryTransactionType[] inventoryTransactionTypes) {
		this.inventoryTransactionTypes = inventoryTransactionTypes;
	}

	@Override
	public BaseObject[] getResponse() {
		return inventoryTransactionTypes;
	}
	
	
}
