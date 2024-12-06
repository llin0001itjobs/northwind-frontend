package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Privilege;

public class PrivilegeResponse extends BaseResponse{

	private Privilege[] privileges;

	public Privilege[] getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privilege[] privileges) {
		this.privileges = privileges;
	}

	@Override
	public BaseObject[] getResponse() {
		return privileges;
	}
	
}
