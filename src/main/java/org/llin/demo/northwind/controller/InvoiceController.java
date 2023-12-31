package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Invoice;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoice")
public class InvoiceController<T extends Invoice> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/invoice.xslt";
	private static final String JSON = "./xslt/sample/invoice.json";
	private Invoice[] invoices = {};

	@SuppressWarnings("unchecked")
	public InvoiceController() {			
		_type = (Class<T[]>) invoices.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllCustomerOrders() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("invoice"));
		mv.addObject(TITLE, "Invoice");
		return mv;
	}

	@Override
	void loadConfigList() {						
		_configList.add("CustomerOrder");					
	}
}

