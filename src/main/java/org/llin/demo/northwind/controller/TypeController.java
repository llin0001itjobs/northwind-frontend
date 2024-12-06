package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/type")
public class TypeController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles  {

	@GetMapping("/list")
	public ModelAndView getAllTypeStates() {
		handleRequest();
		modelAndView.addObject(INVENTORY_TRANSACTION_TYPES,modelViewCache.getObjectArray(INVENTORY_TRANSACTION_TYPE));
		modelAndView.addObject(ORDER_DETAIL_STATUSES,modelViewCache.getObjectArray(ORDER_DETAIL_STATUS));		
		modelAndView.addObject(ORDER_STATUSES,modelViewCache.getObjectArray(ORDER_STATUS));
		modelAndView.addObject(ORDER_TAX_STATUSES,modelViewCache.getObjectArray(ORDER_TAX_STATUS));
		modelAndView.addObject(PAYMENT_TYPES,modelViewCache.getObjectArray(PAYMENT_TYPE));
		modelAndView.addObject(PURCHASE_ORDER_STATUSES,modelViewCache.getObjectArray(PURCHASE_ORDER_STATUS));
		modelAndView.addObject(TYPE_STATES, modelViewCache.getObjectArray(TYPE_STATE));
		modelAndView.addObject(TITLE, TITLE_TYPE);
		modelAndView.setViewName("entities/type");
		return modelAndView;
	}
		
}	
