package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OrderTaxStatus extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;
	
	private String taxStatusName;

	public OrderTaxStatus() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaxStatusName() {
		return taxStatusName;
	}

	public void setTaxStatusName(String taxStatusName) {
		this.taxStatusName = taxStatusName;
	}

	
	@Override
	public String toString() {
		return "\nOrderTaxStatus [id=" + id + ", taxStatusName=" + taxStatusName + super.toString() + "]";
	}

}
