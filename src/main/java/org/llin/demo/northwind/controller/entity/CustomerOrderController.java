package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.CustomerService;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.OrderTaxStatusService;
import org.llin.demo.northwind.service.entity.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final CustomerOrderService customerOrderService;
	private final CustomerService customerService;
	private final EmployeeService employeeService;
	private final ShipperService shipperService;
	private final OrderStatusService orderStatusService;
	private final OrderTaxStatusService orderTaxStatusService;
	
	@Autowired
	public CustomerOrderController(CustomerOrderService customerOrderService, CustomerService customerService, EmployeeService employeeService,
			ShipperService shipperService, OrderStatusService orderStatusService, OrderTaxStatusService orderTaxStatusService) {
		this.customerOrderService = customerOrderService;
		this.customerService = customerService;
		this.employeeService = employeeService;
		this.shipperService = shipperService;
		this.orderStatusService = orderStatusService;
		this.orderTaxStatusService = orderTaxStatusService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllCustomerOrders() {	
		handleRequest();
		modelAndView.addObject(CUSTOMER_ORDERS,customerOrderService.findAll());
		modelAndView.addObject(CUSTOMERS,customerService.findAll());
		modelAndView.addObject(EMPLOYEES,employeeService.findAll());
		modelAndView.addObject(SHIPPERS,shipperService.findAll());
		modelAndView.addObject(ORDER_STATUSES,orderStatusService.findAll());
		modelAndView.addObject(ORDER_TAX_STATUSES,orderTaxStatusService.findAll());
		modelAndView.addObject(TITLE, TITLE_CUSTOMER_ORDER);
		modelAndView.setViewName("entities/customerOrder");
		return modelAndView;
	}
	
}

