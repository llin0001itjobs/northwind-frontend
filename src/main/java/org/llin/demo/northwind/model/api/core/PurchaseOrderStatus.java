package org.llin.demo.northwind.model.api.core;

public class PurchaseOrderStatus extends BaseObject {
	
	private String status;

	public PurchaseOrderStatus() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "\nPurchaseOrderStatus [status=" + status + super.toString() + "]";
	}

}
