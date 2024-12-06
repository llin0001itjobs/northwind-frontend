package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class _HomeController<T extends BaseObject> extends _BaseController<T> {
	

	
    @GetMapping("/home")
    public ModelAndView show() {
    	handleRequest();
    	modelAndView.setViewName("home");
		return modelAndView; 
    }
    
}
