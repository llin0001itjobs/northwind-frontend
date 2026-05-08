package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetailStatus")
public class OrderDetailStatusController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllOrderDetailStatuses() {
		handleRequest();
		modelAndView.addObject(ORDER_DETAIL_STATUSES,modelViewCache.getObjectArray(ORDER_DETAIL_STATUS));
		modelAndView.addObject(TITLE, TITLE_ORDER_DETAIL_STATUS);
		modelAndView.setViewName("entities/orderDetailStatus");
		return modelAndView;
	}

}

 