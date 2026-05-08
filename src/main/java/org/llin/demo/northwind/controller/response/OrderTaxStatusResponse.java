package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.OrderTaxStatus;

public class OrderTaxStatusResponse extends BaseResponse {

	OrderTaxStatus[] orderTaxStatuses;

	public OrderTaxStatus[] getOrderTaxStatuses() {
		return orderTaxStatuses;
	}

	public void setOrderTaxStatuses(OrderTaxStatus[] orderTaxStatuses) {
		this.orderTaxStatuses = orderTaxStatuses;
	}

	@Override
	public EntityObject[] getResponse() {
		return orderTaxStatuses;
	}
}
