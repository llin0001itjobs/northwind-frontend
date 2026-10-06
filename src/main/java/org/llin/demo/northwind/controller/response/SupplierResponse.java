package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.model.entity.Supplier;

public class SupplierResponse extends BaseResponse {

	private Supplier[] suppliers;

	public Supplier[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Supplier[] suppliers) {
		this.suppliers = suppliers;
	}

	@Override
	public _EntityObject[] getResponse() {
		return suppliers;
	}

}