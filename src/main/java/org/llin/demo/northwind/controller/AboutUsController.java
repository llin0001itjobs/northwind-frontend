package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.entity.EntityController;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AboutUsController<T extends EntityObject> extends EntityController<T> {

	@GetMapping("/aboutUs")
	public ModelAndView execute() {
		getModelAndView().addObject(ACTIVE_NAV_ITEM, "nav-item-aboutUs");
		getModelAndView().setViewName("aboutUs");
		return getModelAndView();
	}
}
