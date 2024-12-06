package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.OrderDetail;

public class OrderDetailResponse extends BaseResponse {

	private OrderDetail[] orderDetails;

	public OrderDetail[] getorderDetails() {
		return orderDetails;
	}

	public void setorderDetails(OrderDetail[] orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public BaseObject[] getResponse() {
		return orderDetails;
	}
	
}
