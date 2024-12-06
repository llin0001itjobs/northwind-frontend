package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.OrderStatus;

public class OrderStatusResponse extends BaseResponse {

	private OrderStatus[] orderStatuses;

	public OrderStatus[] getOrderStatuses() {
		return orderStatuses;
	}

	public void setOrderStatuses(OrderStatus[] orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	@Override
	public BaseObject[] getResponse() {
		return orderStatuses;
	}
}
