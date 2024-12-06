package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransaction")
public class InventoryTransactionController<T extends BaseObject> extends _BaseController<T> implements _Classes, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		handleRequest();
		modelAndView.addObject(INVENTORY_TRANSACTIONS,modelViewCache.getObjectArray(INVENTORY_TRANSACTION));
		modelAndView.addObject(CUSTOMER_ORDERS,modelViewCache.getObjectArray(CUSTOMER_ORDER));	
		modelAndView.addObject(PRODUCTS,modelViewCache.getObjectArray(PRODUCT));
		modelAndView.addObject(PURCHASE_ORDERS,modelViewCache.getObjectArray(PURCHASE_ORDER));
		modelAndView.addObject(INVENTORY_TRANSACTION_TYPES,modelViewCache.getObjectArray(INVENTORY_TRANSACTION_TYPE));
		modelAndView.addObject(TITLE, TITLE_INVENTORY_TRANSACTION); 
		modelAndView.setViewName("entities/inventoryTransaction");
		return modelAndView;
	}

}

