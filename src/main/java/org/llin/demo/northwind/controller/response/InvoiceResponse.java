package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Invoice;

public class InvoiceResponse extends BaseResponse {

	private Invoice[] invoices;

	public Invoice[] getInvoices() {
		return invoices;
	}

	public void setInvoices(Invoice[] invoices) {
		this.invoices = invoices;
	}

	@Override
	public BaseObject[] getResponse() {
		return invoices;
	}
	
}
