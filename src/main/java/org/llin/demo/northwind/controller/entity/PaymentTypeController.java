package org.llin.demo.northwind.controller.entity;

import java.util.List;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/paymentType")
public class PaymentTypeController<T extends _EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final PaymentTypeService service;
	
    @Autowired
    public PaymentTypeController(PaymentTypeService service) { 
        this.service = service;
    }

	@GetMapping("/list")
	public ModelAndView getAllPaymentTypes() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PaymentTypeDto> list = service.findById(id);

		if (!list.isEmpty()) {
			mv.addObject(PAYMENT_TYPE, list.get(0));
			mv.setViewName("paymentTypes/detail");
		} else {
			mv.setViewName("error/404");
		}

		return mv;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(PAYMENT_TYPES,service.findAll());
		mv.addObject("activeMenuSection", "type");
		mv.addObject("currentEntityPath", "paymentType");
		mv.addObject(TITLE, TITLE_PAYMENT_TYPE);
		mv.setViewName("entity/paymentType");
		return mv;		
	}
}

 