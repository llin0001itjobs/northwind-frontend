package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.entity.EntityController;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController<T extends EntityObject> extends EntityController<T> {
		
    @GetMapping("/home")
    public ModelAndView show() {
    	handleRequest();
    	getModelAndView().addObject(ACTIVE_NAV_ITEM, "nav-item-home");
    	getModelAndView().setViewName("home");
    	
		return getModelAndView(); 
    }
    
}
