package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.PurchaseOrderDetail;

public class PurchaseOrderDetailResponse extends BaseResponse {

	private PurchaseOrderDetail[] purchaseOrderDetails;

	public PurchaseOrderDetail[] getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public void setPurchaseOrderDetails(PurchaseOrderDetail[] purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

	@Override
	public BaseObject[] getResponse() {
		return purchaseOrderDetails;
	}

}