package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.PurchaseOrderDetail;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderDetail")
public class PurchaseOrderDetailController<T extends PurchaseOrderDetail> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/purchaseOrderDetail.xslt";	
	private static final String JSON = "./xslt/sample/purchaseOrderDetail.json";
	private PurchaseOrderDetail[] purchaseOrderDetails = {};
		
	@SuppressWarnings("unchecked")
	public PurchaseOrderDetailController() {	
		_type = (Class<T[]>) purchaseOrderDetails.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderDetails() {
		return getAllObjects("purchaseOrderDetail");
	}                         

	@Override
	void loadConfigList() {
		_configList.add("InventoryTransaction");
		_configList.add("Product");		
		_configList.add("PurchaseOrder");		
	}

}

