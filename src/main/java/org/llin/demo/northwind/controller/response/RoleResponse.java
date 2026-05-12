package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.Role;

public class RoleResponse extends BaseResponse {

	private Role[] roles;

	public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

	@Override
	public EntityObject[] getResponse() {
		return roles;
	}

}