package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Customer;

public class CustomerResponse extends BaseResponse {
	
    private Customer[] customers;

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	@Override
	public BaseObject[] getResponse() {
		return customers;
	}


}
