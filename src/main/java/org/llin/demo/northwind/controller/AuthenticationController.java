package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllAuthentications() {
		handleRequest();
		modelAndView.addObject(AUTHENTICATION,modelViewCache.getObjectArray(AUTHENTICATION));		    
		modelAndView.addObject(TITLE, TITLE_AUTHENTICATION);	
		modelAndView.setViewName("entities/authentication");
		return modelAndView;
	}



}
