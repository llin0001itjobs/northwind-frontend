package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
import org.llin.demo.northwind.service.entity.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final PurchaseOrderService purchaseOrderService;
	private final EmployeeService employeeService;
	private final SupplierService supplierService;
	private final OrderStatusService orderStatusService;
	
	@Autowired
	public PurchaseOrderController(PurchaseOrderService purchaseOrderService,
			EmployeeService employeeService,
			SupplierService supplierService,
			OrderStatusService orderStatusService) {
		this.purchaseOrderService = purchaseOrderService;
		this.employeeService = employeeService;
		this.supplierService = supplierService;
		this.orderStatusService = orderStatusService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrders() {
		handleRequest();
		modelAndView.addObject(PURCHASE_ORDERS,purchaseOrderService.findAll());
		modelAndView.addObject(EMPLOYEES,employeeService.findAll());
		modelAndView.addObject(SUPPLIERS,supplierService.findAll());
		modelAndView.addObject(ORDER_STATUSES,orderStatusService.findAll());
		modelAndView.addObject(TITLE, TITLE_PURCHASE_ORDER);
		modelAndView.setViewName("entities/purchaseOrder");
		return modelAndView;
	}

}
