package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Role;

public class RoleResponse extends BaseResponse {

	private Role[] roles;

	public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

	@Override
	public BaseObject[] getResponse() {
		return roles;
	}

}