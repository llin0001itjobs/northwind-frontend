package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InventoryTransactionService;
import org.llin.demo.northwind.service.entity.InventoryTransactionTypeService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransaction")
public class InventoryTransactionController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final InventoryTransactionService inventoryTransactionService;
	private final CustomerOrderService customerOrderService;	
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;
	private final InventoryTransactionTypeService inventoryTransactionTypeService;

	@Autowired
	public InventoryTransactionController(InventoryTransactionService inventoryTransactionService,
			CustomerOrderService customerOrderService, 
			ProductService productService,
			PurchaseOrderService purchaseOrderService, InventoryTransactionTypeService inventoryTransactionTypeService) {
		this.inventoryTransactionService = inventoryTransactionService;
		this.customerOrderService = customerOrderService;		
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
		this.inventoryTransactionTypeService = inventoryTransactionTypeService;
	}
		
	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		handleRequest();
		modelAndView.addObject(INVENTORY_TRANSACTIONS,inventoryTransactionService.findAll());
		modelAndView.addObject(CUSTOMER_ORDERS,customerOrderService.findAll());	
		modelAndView.addObject(PRODUCTS,productService.findAll());
		modelAndView.addObject(PURCHASE_ORDERS,purchaseOrderService.findAll());
		modelAndView.addObject(INVENTORY_TRANSACTION_TYPES,inventoryTransactionTypeService.findAll());
		modelAndView.addObject(TITLE, TITLE_INVENTORY_TRANSACTION); 
		modelAndView.setViewName("entities/inventoryTransaction");
		return modelAndView;
	}

}

