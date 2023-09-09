package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.InventoryTransaction;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransaction")
public class InventoryTransactionController<T extends InventoryTransaction> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/inventoryTransaction.xslt";	
	private static final String JSON = "./xslt/sample/inventoryTransaction.json";
	private InventoryTransaction[] inventoryTransactions = {};
		
	@SuppressWarnings("unchecked")
	public InventoryTransactionController() {	
		_type = (Class<T[]>) inventoryTransactions.getClass();	
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		ModelAndView mav = new ModelAndView(); 
		mav = getAllObjects("inventoryTransaction");	
		return mav;
	}

	@Override
	void loadConfigList() {						
		_configList.add("CustomerOrder");			
		_configList.add("Employee");
		_configList.add("InventoryTransactionType");
		_configList.add("Product");
		_configList.add("PurchaseOrder");		
	}
	
}

