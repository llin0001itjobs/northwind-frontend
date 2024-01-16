package org.llin.demo.northwind.model;

public class PaymentType extends BaseObject {
	
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
		return "\nPaymentType [type=" + type + ", description=" + description + super.toString() + "]";
	}

	
}
