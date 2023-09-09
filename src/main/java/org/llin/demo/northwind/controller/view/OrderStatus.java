package org.llin.demo.northwind.controller.view;

public class OrderStatus extends BaseObject {

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
