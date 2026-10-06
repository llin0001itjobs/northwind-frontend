package org.llin.demo.northwind.model.entity;

public class Company extends _EntityObject {
			
	private String name;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company  name=" + name + ", " + super.toString() +"]";
	}


}
