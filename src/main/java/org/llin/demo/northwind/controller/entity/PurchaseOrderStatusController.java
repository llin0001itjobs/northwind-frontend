package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderStatus")
public class PurchaseOrderStatusController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderStatuses() {
		handleRequest();
		modelAndView.addObject(PURCHASE_ORDER_STATUSES,modelViewCache.getObjectArray(PURCHASE_ORDER_STATUS));
		modelAndView.addObject(TITLE, TITLE_PURCHASE_ORDER_STATUS);
		modelAndView.setViewName("entities/purchaseOrderStatus");
		return modelAndView;
	}                         


}

