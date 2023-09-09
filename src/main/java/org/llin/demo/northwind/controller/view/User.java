package org.llin.demo.northwind.controller.view;

public class User  extends BaseObject {

	private String user;
	private String password;

	public User() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "\nUser [user=" + user + ", password=" + password + super.toString() + "]";
	}

}
