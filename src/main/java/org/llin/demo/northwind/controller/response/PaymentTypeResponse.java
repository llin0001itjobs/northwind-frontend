package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.PaymentType;

public class PaymentTypeResponse extends BaseResponse {

	private PaymentType[] paymentTypes;

	public PaymentType[] getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentType[] paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	@Override
	public EntityObject[] getResponse() {
		return paymentTypes;
	}
	
}
