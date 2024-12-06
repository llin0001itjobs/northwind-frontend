package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.PurchaseOrderStatus;

public class PurchaseOrderStatusResponse extends BaseResponse {

	private PurchaseOrderStatus[] purchaseOrderStatuses;

	public PurchaseOrderStatus[] getPurchaseOrderStatuses() {
		return purchaseOrderStatuses;
	}

	public void setPurchaseOrderStatuses(PurchaseOrderStatus[] purchaseOrderStatuses) {
		this.purchaseOrderStatuses = purchaseOrderStatuses;
	}

	@Override
	public BaseObject[] getResponse() {
		return purchaseOrderStatuses;
	}

}
