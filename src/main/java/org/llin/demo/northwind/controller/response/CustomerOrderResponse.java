package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.CustomerOrder;

public class CustomerOrderResponse extends BaseResponse {

    private CustomerOrder[] customerOrders;

	public CustomerOrder[] getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(CustomerOrder[] customerOrders) {
		this.customerOrders = customerOrders;
	}

	@Override
	public BaseObject[] getResponse() {
		return customerOrders;
	}
}
