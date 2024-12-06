package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
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
	public BaseObject[] getResponse() {
		return orderTaxStatuses;
	}
}
