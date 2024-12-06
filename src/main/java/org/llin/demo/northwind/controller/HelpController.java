package org.llin.demo.northwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/help")
public class HelpController {
	
	@GetMapping("/main")
	public ModelAndView execute() {
		ModelAndView mv = new ModelAndView("help-main");
		
		return mv;
	}
}
