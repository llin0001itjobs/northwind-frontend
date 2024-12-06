package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.OrderDetailStatus;

public class OrderDetailStatusResponse extends BaseResponse {

	private OrderDetailStatus[] orderDetailStatuses;

	public OrderDetailStatus[] getOrderDetailStatuses() {
		return orderDetailStatuses;
	}

	public void setOrderDetailStatuses(OrderDetailStatus[] orderDetailStatuses) {
		this.orderDetailStatuses = orderDetailStatuses;
	}

	@Override
	public BaseObject[] getResponse() {
		return orderDetailStatuses;
	}
}
