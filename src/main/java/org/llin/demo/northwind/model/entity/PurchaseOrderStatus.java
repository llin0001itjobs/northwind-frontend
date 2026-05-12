package org.llin.demo.northwind.model.entity;

public class PurchaseOrderStatus extends EntityObject {
	
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
