package org.llin.demo.northwind.model.api.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PurchaseOrderStatus extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;
	private String status;

	public PurchaseOrderStatus() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "\nPurchaseOrderStatus [id=" + id + " status=" + status + super.toString() + "]";
	}

}
