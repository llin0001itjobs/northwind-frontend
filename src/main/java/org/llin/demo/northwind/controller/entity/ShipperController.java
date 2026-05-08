package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shipper")
public class ShipperController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllShippers() {
		handleRequest();
		modelAndView.addObject(SHIPPERS,modelViewCache.getObjectArray(SHIPPER));
		modelAndView.addObject(COMPANIES,modelViewCache.getObjectArray(COMPANY));
		modelAndView.addObject(TITLE, TITLE_SHIPPER);
		modelAndView.setViewName("entities/shipper");
		return modelAndView;
	}

}
