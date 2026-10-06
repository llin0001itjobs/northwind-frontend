package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.model.entity.Invoice;

public class InvoiceResponse extends BaseResponse {

	private Invoice[] invoices;

	public Invoice[] getInvoices() {
		return invoices;
	}

	public void setInvoices(Invoice[] invoices) {
		this.invoices = invoices;
	}

	@Override
	public _EntityObject[] getResponse() {
		return invoices;
	}
	
}
