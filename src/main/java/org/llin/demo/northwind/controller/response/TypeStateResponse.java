package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.TypeState;

public class TypeStateResponse extends BaseResponse {

	private TypeState[] typeStates;

	public TypeState[] getTypeStates() {
		return typeStates;
	}

	public void setTypeStates(TypeState[] typeStates) {
		this.typeStates = typeStates;
	}

	@Override
	public BaseObject[] getResponse() {
		return typeStates;
	}

}