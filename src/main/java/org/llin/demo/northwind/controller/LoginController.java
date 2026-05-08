package org.llin.demo.northwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/northwind/login")
	public String login(ModelAndView model) {
		// CSRF token is automatically added to the model by Spring Security
		return "login"; // Maps to login.html in templates folder
	}
}
