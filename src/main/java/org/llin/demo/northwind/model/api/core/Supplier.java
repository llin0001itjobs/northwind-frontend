package org.llin.demo.northwind.model.api.core;

public class Supplier extends BaseEntity {

	private Supplier[] self = {};
	
	public Supplier() {
		super();
		_type = self.getClass();
		label = "Supplier"; 
	}
	
	@Override
	public String toString() {
		return "\nSupplier [" + super.toString() + "]";
	}

}
