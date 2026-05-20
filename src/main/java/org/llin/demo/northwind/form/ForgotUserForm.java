package org.llin.demo.northwind.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotUserForm {
	
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String userName;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}