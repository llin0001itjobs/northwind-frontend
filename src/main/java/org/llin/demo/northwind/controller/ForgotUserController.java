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

import jakarta.annotation.PostConstruct;

@Controller
public class ForgotUserController {

    @Autowired
    private PropertyDefaultProperties props;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private String subjectForgotUser;
    private String textForgotUser;

    @PostConstruct
    private void init() {
        subjectForgotUser = props.getApp().getMail().getSubject().getForgotUser();
        textForgotUser = props.getApp().getMail().getText().getForgotUser();
    }

    @GetMapping("/forgotuser")
    public String showForgotUser(Model model) {
        model.addAttribute("user", new User());
        return "page-forgot-user";
    }

    @PostMapping("/forgotuser")
    public String sendUsername(@ModelAttribute("user") User user, Model model) {
        Map<String, String> map = new HashMap<>();

        String email = user.getEmail() == null ? "" : user.getEmail().trim();
        if (email.isEmpty()) {
            map.put("errors", "Email is required.");
            model.addAttribute("message", map);
            model.addAttribute("user", user);
            return "page-forgot-user";
        }

        Optional<UserDto> optUser = userService.findByEmail(email);
        // Same response whether or not the address exists (no account enumeration)
        if (optUser.isPresent()) {
            UserDto dto = optUser.get();
            String body = String.format(textForgotUser, dto.username());
            emailService.sendSimpleEmail(dto.email(), subjectForgotUser, body);
        }

        return "redirect:/login?usernameSent=true";
    }
}