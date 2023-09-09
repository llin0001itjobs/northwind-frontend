package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class InventoryTransactionType extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;	
	private String typeName;

	public InventoryTransactionType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "\nInventoryTransactionTypes [id=" + id + ", typeName=" + typeName + super.toString() + "]";
	}
	
}
