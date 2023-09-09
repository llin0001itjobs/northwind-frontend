package org.llin.demo.northwind.model.api.core;

public class Employee extends BaseEntity {
			
	private Employee[] self = {};
	
	public Employee() {
		super();
		_type = self.getClass();
		label = "Employee"; 
	}
		
	@Override
	public String toString() {
		return "\nEmployee [" + super.toString() + "]";
	}

}
