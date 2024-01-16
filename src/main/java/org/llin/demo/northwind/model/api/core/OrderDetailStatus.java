package org.llin.demo.northwind.model;

public class OrderDetailStatus extends BaseObject {
	
	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "\nOrderDetailStatus [statusName=" + statusName + super.toString() + "]";
	}

}
