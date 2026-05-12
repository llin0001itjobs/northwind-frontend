package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoice")
public class InvoiceController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final InvoiceService invoiceService;
	private final CustomerOrderService customerOrderService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService,
			CustomerOrderService customerOrderService) {
		this.invoiceService = invoiceService;
		this.customerOrderService = customerOrderService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllInvoices() {
		handleRequest();
		modelAndView.addObject(INVOICES,        invoiceService.findAll());
		modelAndView.addObject(CUSTOMER_ORDERS, customerOrderService.findAll());	
		modelAndView.addObject(TITLE, TITLE_INVOICE);
		modelAndView.setViewName("entities/invoice");
		return modelAndView;
	}

}


