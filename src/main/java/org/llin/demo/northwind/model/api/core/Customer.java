package org.llin.demo.northwind.model.api.core;

public class Customer extends BaseEntity {
			
	private Customer[] self = {};
	
	public Customer() {
		super();
		_type = self.getClass();
		label = "Customer"; 
	}
	
	@Override
	public String toString() {
		return "\nCustomer [" + super.toString() + "]";
	}

}
