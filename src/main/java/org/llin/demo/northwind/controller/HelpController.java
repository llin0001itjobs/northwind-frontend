package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.entity._EntityController;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/help")
public class HelpController <T extends EntityObject> extends _EntityController<T> {
	
	@GetMapping("/main")
	public ModelAndView execute() {
		loadMenu();
    	ModelAndView mv = new ModelAndView(getModelAndView().getView());
    	mv.addObject(ACTIVE_NAV_ITEM, "nav-item-help");
    	mv.setViewName("help/main");
    	return mv;
	}
	
}
