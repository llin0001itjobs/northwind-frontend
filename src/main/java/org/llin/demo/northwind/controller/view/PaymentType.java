package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PaymentType extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String type;
	private String description;
	
	public PaymentType() {
		// TODO Auto-generated constructor stub
	}

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
