package org.llin.demo.northwind.controller;

import java.util.List;

import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.llin.demo.northwind.dto.UserDto;
import org.llin.demo.northwind.model.entity.User;
import org.llin.demo.northwind.service.EmailService;
import org.llin.demo.northwind.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

@Controller
public class LoginController {

	@Autowired
	private PropertyDefaultProperties props;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	private String subjectVerified;

	private String textVerified;

	@PostConstruct
	private void init() {
		subjectVerified = props.getApp().getMail().getSubject().getVerified();
		textVerified = props.getApp().getMail().getText().getVerified();
	}

	@GetMapping("/login")
	public String handleLogin(@ModelAttribute("user") User user, @RequestParam(required = false) String register,
			@RequestParam(required = false) String verified, @RequestParam(required = false) String registrationSuccess,
			@RequestParam(required = false) String error, 
			@RequestParam(required = false) String passwordReset, Model model) {

		if (passwordReset != null) {
			model.addAttribute("message", "Password successfully reset.");
		}
		
		if (registrationSuccess != null) {
			model.addAttribute("message", "Registration successful. Please check your email to verify.");
		}

		if (verified != null) {
			model.addAttribute("message", "Email verified. Please log in.");
		}

		if (error != null) {
			model.addAttribute("message", "Invalid verification token.");
		}

		model.addAttribute("user", new User());
		model.addAttribute("register", register != null); // Still toggles form mode
		return "page-login";
	}


	@PostMapping("/setPassword")
	public String setPassword(@ModelAttribute("user") User user, @RequestParam(required = false) String success,
			@RequestParam(required = false) String error, Model model) {

		if (success != null) {
			model.addAttribute("message", "New password set successfully. Please check your email to verify.");
		}

		model.addAttribute("user", user);
		return "page-login";
	}

	@GetMapping("/verify")
	public String verifyEmail(@RequestParam("token") String token) {

		User user = new User();
		List<UserDto> list = userService.findByVerificationToken(token);
		
		if (!list.isEmpty()) {
			UserDto dto = list.get(0);
			
			user.setEmail(dto.email());			
			user.setEnabled(true);
			user.setEmailVerified(true);
			user.setVerificationToken(token);			
			userService.update(dto.id(), dto);

			// Send confirmed email
			emailService.sendSimpleEmail(dto.email(), subjectVerified, textVerified);

			return "redirect:/login?verified=true";
		}
		return "redirect:/login?error=invalidToken";
	}

}

