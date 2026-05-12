package org.llin.demo.northwind.model.entity;

public class InventoryTransactionType extends EntityObject {
	
	private String typeName;

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
