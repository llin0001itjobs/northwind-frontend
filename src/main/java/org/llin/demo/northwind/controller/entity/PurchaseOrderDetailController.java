package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InventoryTransactionService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.PurchaseOrderDetailService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderDetail")
public class PurchaseOrderDetailController<T extends EntityObject> extends EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final PurchaseOrderDetailService purchaseOrderDetailService;
	private final CustomerOrderService customerOrderService;
	private final InventoryTransactionService inventoryTransactionService;
	private final OrderStatusService orderStatusService;
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;

	@Autowired
	public PurchaseOrderDetailController(PurchaseOrderDetailService purchaseOrderDetailService,
			CustomerOrderService customerOrderService, InventoryTransactionService inventoryTransactionService,
			OrderStatusService orderStatusService, ProductService productService,
			PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderDetailService = purchaseOrderDetailService;
		this.customerOrderService = customerOrderService;
		this.inventoryTransactionService = inventoryTransactionService;
		this.orderStatusService = orderStatusService;
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderDetails() {
		handleRequest();
		modelAndView.addObject(PURCHASE_ORDER_DETAILS, purchaseOrderDetailService.findAll());
		modelAndView.addObject(CUSTOMER_ORDERS, customerOrderService.findAll());
		modelAndView.addObject(INVENTORY_TRANSACTIONS, inventoryTransactionService.findAll());
		modelAndView.addObject(ORDER_STATUSES, orderStatusService.findAll());
		modelAndView.addObject(PRODUCTS, productService.findAll());
		modelAndView.addObject(PURCHASE_ORDERS, purchaseOrderService.findAll());
		modelAndView.addObject(TITLE, TITLE_PURCHASE_ORDER_DETAIL);
		modelAndView.setViewName("entities/purchaseOrderDetail");
		return modelAndView;
	}

}
