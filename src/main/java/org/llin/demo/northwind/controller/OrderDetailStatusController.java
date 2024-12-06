package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetailStatus")
public class OrderDetailStatusController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllOrderDetailStatuses() {
		handleRequest();
		modelAndView.addObject(ORDER_DETAIL_STATUSES,modelViewCache.getObjectArray(ORDER_DETAIL_STATUS));
		modelAndView.addObject(TITLE, TITLE_ORDER_DETAIL_STATUS);
		modelAndView.setViewName("entities/orderDetailStatus");
		return modelAndView;
	}

}

 