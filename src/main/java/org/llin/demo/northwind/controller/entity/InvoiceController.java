package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoice")
public class InvoiceController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllInvoices() {
		handleRequest();
		modelAndView.addObject(INVOICES,        modelViewCache.getObjectArray(INVOICE));
		modelAndView.addObject(CUSTOMER_ORDERS, modelViewCache.getObjectArray(CUSTOMER_ORDER));	
		modelAndView.addObject(TITLE, TITLE_INVOICE);
		modelAndView.setViewName("entities/invoice");
		return modelAndView;
	}

}


