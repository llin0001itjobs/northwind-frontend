package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderDetail")
public class PurchaseOrderDetailController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderDetails() {
		handleRequest();
		modelAndView.addObject(PURCHASE_ORDER_DETAILS,modelViewCache.getObjectArray(PURCHASE_ORDER_DETAIL));
		modelAndView.addObject(CUSTOMER_ORDERS,modelViewCache.getObjectArray(CUSTOMER_ORDER));
		modelAndView.addObject(INVENTORY_TRANSACTIONS,modelViewCache.getObjectArray(INVENTORY_TRANSACTION));
		modelAndView.addObject(ORDER_STATUSES,modelViewCache.getObjectArray(ORDER_STATUS));
		modelAndView.addObject(PRODUCTS,modelViewCache.getObjectArray(PRODUCT));
		modelAndView.addObject(PURCHASE_ORDERS,modelViewCache.getObjectArray(PURCHASE_ORDER));
		modelAndView.addObject(TITLE, TITLE_PURCHASE_ORDER_DETAIL);
		modelAndView.setViewName("entities/purchaseOrderDetail");
		return modelAndView;
	}                         

}

