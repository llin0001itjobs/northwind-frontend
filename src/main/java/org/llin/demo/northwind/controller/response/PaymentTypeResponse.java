package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.PaymentType;

public class PaymentTypeResponse extends BaseResponse {

	private PaymentType[] paymentTypes;

	public PaymentType[] getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType[] paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	@Override
	public BaseObject[] getResponse() {
		return paymentTypes;
	}
	
}
