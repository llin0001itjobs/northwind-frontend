package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.model.entity.Privilege;

public class PrivilegeResponse extends BaseResponse{

	private Privilege[] privileges;

	public Privilege[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privilege[] privileges) {
		this.privileges = privileges;
	}

	@Override
	public _EntityObject[] getResponse() {
		return privileges;
	}
	
}
