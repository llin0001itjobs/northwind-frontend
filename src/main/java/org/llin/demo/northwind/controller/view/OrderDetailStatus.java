package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OrderDetailStatus extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;
	private String statusName;

	public OrderDetailStatus() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "\nOrderDetailStatus [id=" + id + " statusName=" + statusName + super.toString() + "]";
	}

}
