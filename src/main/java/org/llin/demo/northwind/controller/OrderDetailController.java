package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.OrderDetail;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController<T extends OrderDetail> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/orderDetail.xslt";
	private static final String JSON = "./xslt/sample/orderDetail.json";
	private OrderDetail[] orderDetails = {};
	
	@SuppressWarnings("unchecked")
	public OrderDetailController() {			
		_type = (Class<T[]>) orderDetails.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllOrderDetails() {
		return getAllObjects("orderDetail");
	}

	@Override
	void loadConfigList() {						
		_configList.add("CustomerOrder");			
		_configList.add("InventoryTransactionType");
		_configList.add("OrderStatus");
		_configList.add("Product");
		_configList.add("PurchaseOrder");		
	}	

}

 