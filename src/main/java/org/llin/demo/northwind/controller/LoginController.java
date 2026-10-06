package org.llin.demo.northwind.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
	public String handleLogin(@ModelAttribute("user") User user, 
			@RequestParam(required = false) String error,
			@RequestParam(required = false) String invalidToken,
			@RequestParam(required = false) String passwordReset,
			@RequestParam(required = false) String register,
			@RequestParam(required = false) String registrationSuccess,
			@RequestParam(required = false) String usernameSent,
			@RequestParam(required = false) String verified, 
			 
			Model model) {

		Map<String,String> map = new HashMap<>();
		
		if (error != null) {			
			map.put("loginFailed","Login Failed. Try again.");
			model.addAttribute("message", map);
		}
		
		if (invalidToken != null) {			
			map.put("invalidToken","Token is invalid.");
			model.addAttribute("message", map);
		}
		
		if (passwordReset != null) {
			map.put("passwordReset", "Password successfully reset.");
			model.addAttribute("message", map);
		}

		if (registrationSuccess != null) {
			map.put("registrationSuccessful","Registration successful. Please check your email to verify.");
			model.addAttribute("message", map);
		}
		if (usernameSent != null) {
		    map.put("usernameSent", "If that email is on file, the username was sent.");
		    model.addAttribute("message", map);
		}
		
		if (verified != null) {
			map.put("verified","Email verified. Please log in.");
			model.addAttribute("message", map);
		}

		if (user.getUsername() == null && user.getPassword() == null) {
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", user); // Keeps the values the user typed in
		}
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
	    Optional<UserDto> optUserDto = userService.findByVerificationToken(token);
	    if (optUserDto.isEmpty()) {
	        return "redirect:/login?invalidToken=true";
	    }

	    UserDto dto = optUserDto.get();
	    UserDto verified = new UserDto(
	            dto.id(),
	            dto.roles(),
	            dto.username(),
	            dto.password(),
	            dto.email(),
	            true,   // enabled
	            true,   // emailVerified
	            null    // consume the token
	    );

	    userService.update(dto.id(), verified);
	    emailService.sendSimpleEmail(dto.email(), subjectVerified, textVerified);
	    return "redirect:/login?verified=true";
	}

}
