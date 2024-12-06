package org.llin.demo.northwind.model;

public class OrderTaxStatus extends BaseObject {
	
	private String taxStatusName;

	public OrderTaxStatus() {
		super();
	}
	
	public String getTaxStatusName() {
		return taxStatusName;
	}

	public void setTaxStatusName(String taxStatusName) {
		this.taxStatusName = taxStatusName;
	}

	
	@Override
	public String toString() {
		return "\nOrderTaxStatus [taxStatusName=" + taxStatusName + super.toString() + "]";
	}

}
