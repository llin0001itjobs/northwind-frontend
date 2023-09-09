package org.llin.demo.northwind.model.api.core;

public class Company extends BaseObject {
	
	private String name;

	private Company[] self = {};
	
	public Company() {
		super();
		_type = self.getClass();
		label = "Company"; 
	}
			
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\nCompany [name=" + name + super.toString() + "]";
	}

}
