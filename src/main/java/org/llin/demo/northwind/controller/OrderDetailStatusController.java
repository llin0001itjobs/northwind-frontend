package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.OrderDetailStatus;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetailStatus")
public class OrderDetailStatusController<T extends OrderDetailStatus> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/orderDetailStatus.xslt";
	private static final String JSON = "./xslt/sample/orderDetailStatus.json";
	
	private OrderDetailStatus[] orderDetailStatuses = {};
	
	@SuppressWarnings("unchecked")
	public OrderDetailStatusController() {			
		_type = (Class<T[]>) orderDetailStatuses.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllOrderDetailStatuses() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("orderDetailStatus"));
		mv.addObject(TITLE, "Order Detail Status");
		return mv;
	}

	@Override
	void loadConfigList() {						
	
	}	

}

 