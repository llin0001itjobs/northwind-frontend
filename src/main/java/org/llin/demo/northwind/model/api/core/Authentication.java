package org.llin.demo.northwind.model;

public class Authentication extends BaseObject {

	private String roleType;
	private String userName;
	private String password;
	
	public Authentication() {
		super();
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
		return "\nAuthentication [roleType=" + roleType + ", userName=" + userName + ", password="
				+ password + "]";
	}

}
