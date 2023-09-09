package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Authentication extends BaseObject {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;	
	private String roleType;
	private String userName;
	private String password;

	public Authentication() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Authentication [id=" + id +" roleType=" + roleType + ", userName=" + userName + ", password="
				+ password + "]";
	}

}
