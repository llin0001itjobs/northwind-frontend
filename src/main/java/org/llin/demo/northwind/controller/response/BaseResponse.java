package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;

public abstract class BaseResponse {

	public abstract EntityObject[] getResponse();

	@Override
	public String toString() {
		return "_BaseResponse []" + getResponse();
	}
	
}
