package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.model.entity.PurchaseOrderDetail;

public class PurchaseOrderDetailResponse extends BaseResponse {

	private PurchaseOrderDetail[] purchaseOrderDetails;

	public PurchaseOrderDetail[] getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public void setPurchaseOrderDetails(PurchaseOrderDetail[] purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}

	@Override
	public _EntityObject[] getResponse() {
		return purchaseOrderDetails;
	}

}