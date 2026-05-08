package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;
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
	public EntityObject[] getResponse() {
		return privileges;
	}
	
}
