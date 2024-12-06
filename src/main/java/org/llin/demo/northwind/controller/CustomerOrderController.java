package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllCustomerOrders() {	
		handleRequest();
		modelAndView.addObject(CUSTOMER_ORDERS,modelViewCache.getObjectArray(CUSTOMER_ORDER));
		modelAndView.addObject(CUSTOMERS,modelViewCache.getObjectArray(CUSTOMER));
		modelAndView.addObject(EMPLOYEES,modelViewCache.getObjectArray(EMPLOYEE));
		modelAndView.addObject(SHIPPERS,modelViewCache.getObjectArray(SHIPPER));
		modelAndView.addObject(ORDER_STATUSES,modelViewCache.getObjectArray(ORDER_STATUS));
		modelAndView.addObject(ORDER_TAX_STATUSES,modelViewCache.getObjectArray(ORDER_TAX_STATUS));
		modelAndView.addObject(TITLE, TITLE_CUSTOMER_ORDER);
		modelAndView.setViewName("entities/customerOrder");
		return modelAndView;
	}
	
}

