package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.model.entity.TypeState;

public class TypeStateResponse extends BaseResponse {

	private TypeState[] typeStates;

	public TypeState[] getTypeStates() {
		return typeStates;
	}

	public void setTypeStates(TypeState[] typeStates) {
		this.typeStates = typeStates;
	}

	@Override
	public _EntityObject[] getResponse() {
		return typeStates;
	}

}