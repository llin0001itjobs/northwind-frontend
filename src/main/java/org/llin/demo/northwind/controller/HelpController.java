package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.entity.EntityController;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/help")
public class HelpController <T extends EntityObject> extends EntityController<T> {
	
	@GetMapping("/main")
	public ModelAndView execute() {
		getModelAndView().addObject(ACTIVE_NAV_ITEM, "nav-item-help");
		getModelAndView().setViewName("help/main");
		return getModelAndView();
	}
	
}
