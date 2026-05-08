package org.llin.demo.northwind;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// Add error message as a query parameter or session attribute
		String errorMessage = "Invalid username or password";
		response.sendRedirect("/login?error=true&message=" + java.net.URLEncoder.encode(errorMessage, "UTF-8"));

	}
}