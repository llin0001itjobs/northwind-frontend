package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/typeState")
public class TypeStateController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles  {

	@GetMapping("/list")
	public ModelAndView getAllTypeStates() {
		handleRequest();
		modelAndView.addObject(TYPE_STATES,modelViewCache.getObjectArray(TYPE_STATE));
		modelAndView.addObject(TITLE, TITLE_TYPE_STATE);
		modelAndView.setViewName("entities/typeState");
		return modelAndView;
	}
		

}	
