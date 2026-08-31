package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.form.SubscribeForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubscribeController {

	@RequestMapping("/subscribe")
	public String subscribe(@ModelAttribute("subscribeForm") SubscribeForm form) {
		return "page-subscribe";
	}
}
