package org.llin.demo.northwind.model;

public class Company extends BaseObject {
		
	private String name;
				
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
