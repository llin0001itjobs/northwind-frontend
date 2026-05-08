package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransaction")
public class InventoryTransactionController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
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

