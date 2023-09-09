package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.PurchaseOrderStatus;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderStatus")
public class PurchaseOrderStatusController<T extends PurchaseOrderStatus> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/purchaseOrderStatus.xslt";	
	private static final String JSON = "./xslt/sample/purchaseOrderStatus.json";
	private PurchaseOrderStatus[] purchaseOrderStatuses = {};
		
	@SuppressWarnings("unchecked")
	public PurchaseOrderStatusController() {	
		_type = (Class<T[]>) purchaseOrderStatuses.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderStatuses() {
		return getAllObjects("purchaseOrderStatus");
	}                         

	@Override
	void loadConfigList() {
		
	}

}

