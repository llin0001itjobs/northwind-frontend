package org.llin.demo.northwind.model.entity;

public class Role extends EntityObject {

	private String type;
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", type=" + type + ", description=" + description + super.toString() + "]";
	}

}