package org.llin.demo.northwind.model;

public class Role extends EntityObject {
	
	private String name;
	private String description;

	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
		this.description = "";
	}
	
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "\nRole [name=" + name + ", description=" + description + super.toString() + "]";
	}

}
