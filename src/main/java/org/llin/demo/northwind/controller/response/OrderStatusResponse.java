package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.OrderStatus;

public class OrderStatusResponse extends BaseResponse {

	private OrderStatus[] orderStatuses;

	public OrderStatus[] getOrderStatuses() {
		return orderStatuses;
	}

	public void setOrderStatuses(OrderStatus[] orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	@Override
	public EntityObject[] getResponse() {
		return orderStatuses;
	}
}
