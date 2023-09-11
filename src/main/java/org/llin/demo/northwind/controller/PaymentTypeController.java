package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.PaymentType;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/paymentType")
public class PaymentTypeController<T extends PaymentType> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/paymentType.xslt";
	private static final String JSON = "./xslt/sample/paymentType.json";
	private PaymentType[] paymentTypes = {};
	
	@SuppressWarnings("unchecked")
	public PaymentTypeController() {			
		_type = (Class<T[]>) paymentTypes.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllPaymentTypes() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("paymentType"));
		mv.addObject(TITLE, "Payment Type");
		return mv;
	}

	@Override
	void loadConfigList() {						
	
	}	

}

 