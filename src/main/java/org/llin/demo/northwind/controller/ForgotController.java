package org.llin.demo.northwind.controller;

import java.util.List;

import org.llin.demo.northwind.dto.UserDto;
import org.llin.demo.northwind.form.ForgotUserForm;
import org.llin.demo.northwind.service.EmailService;
import org.llin.demo.northwind.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
public class ForgotController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/forgotUser")
	public String forgotUser(@ModelAttribute("forgotUserForm") ForgotUserForm form) {
		return "page-forgot-user";
	}

	@PostMapping("/forgotUser")
	public String forgotUserPost(@ModelAttribute("forgotUserForm") @Valid ForgotUserForm form,
			@RequestParam(required = false) String cancel, BindingResult result, Model model) {

		if ("true".equals(cancel)) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return "page-forgot-user"; // redisplay form with errors
		}

		List<UserDto> list = userService.findByEmail(form.getEmail());
		if (list.isEmpty()) {
			result.rejectValue("email", "email.not.exists", "Email does not exist.");
			return "page-forgot-user";
		}

		UserDto dto = list.get(0); // ← fetch real user

		try {
			emailService.sendHtmlEmail(form.getEmail(), "This is your Username",
					emailService.convertToHtml("Username: " + dto.username()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		model.addAttribute("message", "Username has been sent to your email.");
		return "page-forgot-user";

	}

}
