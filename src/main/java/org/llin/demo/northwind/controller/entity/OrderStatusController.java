package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.OrderStatusDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/orderStatus")
public class OrderStatusController<T extends _EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {

	private final OrderStatusService service;
	
    @Autowired
    public OrderStatusController(OrderStatusService service) { 
        this.service = service;
    }
    
	@GetMapping("/list")
	public ModelAndView getAllOrderStatuses() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		Optional<OrderStatusDto> opt = service.findById(id);

		if (opt.isPresent()) {
			mv.addObject(ORDER_STATUS, opt.get());
			mv.setViewName("orderStatuses/detail");
		} else {
			mv.setViewName("error/404");
		}

		return mv;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());			
		mv.addObject(ORDER_STATUSES,service.findAll());
		mv.addObject("activeMenuSection", "type");
		mv.addObject("currentEntityPath", "orderStatus");
		mv.addObject(TITLE, TITLE_ORDER_STATUS);
		mv.setViewName("entity/orderStatus");
		return mv;		
	}
}

 