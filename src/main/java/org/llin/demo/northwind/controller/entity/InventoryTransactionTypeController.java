package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.InventoryTransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransactionType")
public class InventoryTransactionTypeController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	@Autowired
	private InventoryTransactionTypeService service;

	@Autowired
	public InventoryTransactionTypeController(InventoryTransactionTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		handleRequest();
		modelAndView.addObject(INVENTORY_TRANSACTION_TYPES,service.findAll());
		modelAndView.addObject(TITLE, TITLE_INVENTORY_TRANSACTION_TYPE);
		modelAndView.setViewName("entities/inventoryTransactionType");
		return modelAndView;
	}
	
}

