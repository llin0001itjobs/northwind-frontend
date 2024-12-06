package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrders() {
		handleRequest();
		modelAndView.addObject(PURCHASE_ORDERS,modelViewCache.getObjectArray(PURCHASE_ORDER));
		modelAndView.addObject(EMPLOYEES,modelViewCache.getObjectArray(APPROVED_BY));
		modelAndView.addObject(EMPLOYEES,modelViewCache.getObjectArray(CREATED_BY));
		modelAndView.addObject(EMPLOYEES,modelViewCache.getObjectArray(SUBMITTED_BY));
		modelAndView.addObject(SUPPLIERS,modelViewCache.getObjectArray(SUPPLIER));
		modelAndView.addObject(ORDER_STATUSES,modelViewCache.getObjectArray(ORDER_STATUS));
		modelAndView.addObject(TITLE, TITLE_PURCHASE_ORDER);
		modelAndView.setViewName("entities/purchaseOrder");
		return modelAndView;
	}

}
