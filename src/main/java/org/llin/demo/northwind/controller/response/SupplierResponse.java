package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Supplier;

public class SupplierResponse extends BaseResponse {

	private Supplier[] suppliers;

	public Supplier[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Supplier[] suppliers) {
		this.suppliers = suppliers;
	}

	@Override
	public BaseObject[] getResponse() {
		return suppliers;
	}

}