package org.llin.demo.northwind.model.entity;

public class OrderStatus extends EntityObject {
	
	private String statusName;

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
