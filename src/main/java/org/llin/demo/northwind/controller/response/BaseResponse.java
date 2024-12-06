package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;

public abstract class BaseResponse {

	public abstract BaseObject[] getResponse();

	@Override
	public String toString() {
		return "_BaseResponse []" + getResponse();
	}
	
}
