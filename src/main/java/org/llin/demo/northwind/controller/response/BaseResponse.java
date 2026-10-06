package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;

public abstract class BaseResponse {

	public abstract _EntityObject[] getResponse();

	@Override
	public String toString() {
		return "_BaseResponse []" + getResponse();
	}
	
}
