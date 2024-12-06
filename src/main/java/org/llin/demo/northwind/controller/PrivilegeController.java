package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllPrivilege() {
		handleRequest();
		modelAndView.addObject(PRIVILEGES,modelViewCache.getObjectArray(PRIVILEGE));
		modelAndView.addObject(TITLE, TITLE_PRIVILEGE);
		modelAndView.setViewName("entities/privilege");
		return modelAndView;
	}
	
}

