package org.llin.demo.northwind.controller.view;

public class Company extends BaseObject {
	
	private String name;

	public Company() {
		super();
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
