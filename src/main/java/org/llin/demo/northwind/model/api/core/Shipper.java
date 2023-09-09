package org.llin.demo.northwind.model.api.core;

public class Shipper extends BaseEntity {

	private Shipper[] self = {};
	
	public Shipper() {
		super();
		_type = self.getClass();
		label = "Shipper"; 
	}
	
	@Override
	public String toString() {
		return "\nShipper [" + super.toString() + "]";
	}

}
