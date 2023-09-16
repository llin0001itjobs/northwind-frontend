package org.llin.demo.northwind.model.api.core;

public class OrderStatus extends BaseObject {

	String[] vals = {"id","statusName"};
	
	private String statusName;

	public OrderStatus() {
		super();
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "\nOrderStatus [statusName=" + statusName + super.toString() + "]";
	}

}
