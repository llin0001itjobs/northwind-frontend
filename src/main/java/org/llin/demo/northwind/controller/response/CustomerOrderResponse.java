package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.CustomerOrder;
import org.llin.demo.northwind.model.entity.EntityObject;

public class CustomerOrderResponse extends BaseResponse {

    private CustomerOrder[] customerOrders;

	public CustomerOrder[] getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(CustomerOrder[] customerOrders) {
		this.customerOrders = customerOrders;
	}

	@Override
	public EntityObject[] getResponse() {
		return customerOrders;
	}
}
