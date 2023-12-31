package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.OrderTaxStatus;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderTaxStatus")
public class OrderTaxStatusController<T extends OrderTaxStatus> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/orderTaxStatus.xslt";
	private static final String JSON = "./xslt/sample/orderTaxStatus.json";
	private OrderTaxStatus[] orderTaxStatuses = {};
	
	@SuppressWarnings("unchecked")
	public OrderTaxStatusController() {			
		_type = (Class<T[]>) orderTaxStatuses.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllOrderTaxStatuses() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("orderTaxStatus"));
		mv.addObject(TITLE, "Order Tax Status");
		return mv;
	}

	@Override
	void loadConfigList() {						
	
	}	

}

 