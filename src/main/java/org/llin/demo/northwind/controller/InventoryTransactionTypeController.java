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
@RequestMapping("/inventoryTransactionType")
public class InventoryTransactionTypeController<T extends InventoryTransaction> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/inventoryTransactionType.xslt";	
	private static final String JSON = "./xslt/sample/inventoryTransactionType.json";
	private InventoryTransaction[] inventoryTransactionTypes = {};
		
	@SuppressWarnings("unchecked")
	public InventoryTransactionTypeController() {	
		_type = (Class<T[]>) inventoryTransactionTypes.getClass();	
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		ModelAndView mav = new ModelAndView(); 
		mav = getAllObjects("inventoryTransactionType");	
		return mav;
	}

	@Override
	void loadConfigList() {						
		
	}
	
}

