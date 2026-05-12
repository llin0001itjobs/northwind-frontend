package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.Customer;
import org.llin.demo.northwind.model.entity.EntityObject;

public class CustomerResponse extends BaseResponse {
	
    private Customer[] customers;

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	@Override
	public EntityObject[] getResponse() {
		return customers;
	}


}
