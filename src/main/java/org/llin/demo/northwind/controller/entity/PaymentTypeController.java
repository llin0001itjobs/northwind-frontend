package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/paymentType")
public class PaymentTypeController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
	
	@GetMapping("/list")
	public ModelAndView getAllPaymentTypes() {
		handleRequest();
		modelAndView.addObject(PAYMENT_TYPES,modelViewCache.getObjectArray(PAYMENT_TYPE));
		modelAndView.addObject(TITLE, TITLE_PAYMENT_TYPE);
		modelAndView.setViewName("entities/paymentType");
		return modelAndView;
	}

}

 