package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Customer;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController<T extends Customer> extends BaseController<T> {

	private static final String XSLT = "./xslt/customer.xslt";
	private static final String JSON = "./xslt/sample/customer.json";
	private Customer[] customers = {};
	
	@SuppressWarnings("unchecked")
	public CustomerController() {
		_type = (Class<T[]>) customers.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));	
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllCustomers() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("customer"));
		mv.addObject(TITLE, "Customer");
		return mv;
	}

	@Override
	void loadConfigList() {
		_configList.add("Company");
	}

}
 