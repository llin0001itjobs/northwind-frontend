package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.PurchaseOrder;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController<T extends PurchaseOrder> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/purchaseOrder.xslt";	
	private static final String JSON = "./xslt/sample/purchaseOrder.json";
	private PurchaseOrder[] purchaseOrders = {};
	
	@SuppressWarnings("unchecked")
	public PurchaseOrderController() {	
		_type = (Class<T[]>) purchaseOrders.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrders() {
		return getAllObjects("purchaseOrder");
	}

	@Override
	void loadConfigList() {		
		_configList.add("OrderStatus");
		_configList.add("Supplier");		
		_configList.add("ApprovedBy");
		_configList.add("CreatedBy");
		_configList.add("SubmittedBy");		
	}	

}

