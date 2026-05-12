package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.PurchaseOrderStatus;

public class PurchaseOrderStatusResponse extends BaseResponse {

	private PurchaseOrderStatus[] purchaseOrderStatuses;

	public PurchaseOrderStatus[] getPurchaseOrderStatuses() {
		return purchaseOrderStatuses;
	}

	public void setPurchaseOrderStatuses(PurchaseOrderStatus[] purchaseOrderStatuses) {
		this.purchaseOrderStatuses = purchaseOrderStatuses;
	}

	@Override
	public EntityObject[] getResponse() {
		return purchaseOrderStatuses;
	}

}
