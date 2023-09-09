package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.CustomerOrder;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController<T extends CustomerOrder> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/customerOrder.xslt";
	private static final String JSON = "./xslt/sample/customerOrder.json";
	private CustomerOrder[] customerOrders = {};

	@SuppressWarnings("unchecked")
	public CustomerOrderController() {			
		_type = (Class<T[]>) customerOrders.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllCustomerOrders() {
		return getAllObjects("customerOrder");
	}

	@Override
	void loadConfigList() {							
		_configList.add("Customer");
		_configList.add("Employee");
		_configList.add("Shipper");
		_configList.add("OrderStatus");
		_configList.add("OrderTaxStatus");		
	}
	
}

