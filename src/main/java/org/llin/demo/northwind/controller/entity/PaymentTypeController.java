package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/paymentType")
public class PaymentTypeController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
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
		ModelAndView mav = createDefaultModelAndView();
		Optional<PaymentTypeDto> opt = service.findById(id);

		if (opt.isPresent()) {
			mav.addObject(PAYMENT_TYPE, opt.get());
			mav.setViewName("paymentTypes/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(PAYMENT_TYPES,service.findAll());
		mv.addObject(TITLE, TITLE_PAYMENT_TYPE);
		mv.setViewName("entities/paymentType");
		return mv;		
	}
}

 