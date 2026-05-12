package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.OrderDetail;

public class OrderDetailResponse extends BaseResponse {

	private OrderDetail[] orderDetails;

	public OrderDetail[] getorderDetails() {
		return orderDetails;
	}

	public void setorderDetails(OrderDetail[] orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public EntityObject[] getResponse() {
		return orderDetails;
	}
	
}
