package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.OrderTaxStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderTaxStatus")
public class OrderTaxStatusController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final OrderTaxStatusService service;
	
    @Autowired
    public OrderTaxStatusController(OrderTaxStatusService service) { 
        this.service = service;
    }
    
	@GetMapping("/list")
	public ModelAndView getAllOrderTaxStatuses() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<OrderTaxStatusDto> opt = service.findById(id);

		if (opt.isPresent()) {
			mav.addObject(ORDER_TAX_STATUS, opt.get());
			mav.setViewName("orderTaxStatuses/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());			
		mv.addObject(ORDER_TAX_STATUSES,service.findAll());
		mv.addObject(TITLE, TITLE_ORDER_TAX_STATUS);
		mv.setViewName("entities/orderTaxStatus");
		return mv;		
	}
	
}

 