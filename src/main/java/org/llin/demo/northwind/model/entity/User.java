package org.llin.demo.northwind.model.entity;

import java.util.List;

public class User extends EntityObject {
	
	private List<Role> roles;
	
	private String username;

	private String password;

	private String email;
	
	private Boolean enabled;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [roles=" + roles + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", enabled=" + enabled + "]";
	}
		
}
