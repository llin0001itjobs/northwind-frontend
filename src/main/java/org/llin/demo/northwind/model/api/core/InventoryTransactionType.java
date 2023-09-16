package org.llin.demo.northwind.model.api.core;

public class InventoryTransactionType extends BaseObject {
	
	private String typeName;

	public InventoryTransactionType() {
		super();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "\nInventoryTransactionTypes [typeName=" + typeName + super.toString() + "]";
	}
	
}
