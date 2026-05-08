package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.PurchaseOrder;

public class PurchaseOrderResponse extends BaseResponse {

	private PurchaseOrder[] purchaseOrders;

	public PurchaseOrder[] getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(PurchaseOrder[] purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	@Override
	public EntityObject[] getResponse() {
		return purchaseOrders;
	}

}
