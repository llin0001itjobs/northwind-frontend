package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderStatus")
public class OrderStatusController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final OrderStatusService service;
	
    @Autowired
    public OrderStatusController(OrderStatusService service) { 
        this.service = service;
    }
    
	@GetMapping("/list")
	public ModelAndView getAllOrderStatuses() {
		handleRequest();
		modelAndView.addObject(ORDER_STATUSES,service.findAll());
		modelAndView.addObject(TITLE, TITLE_ORDER_STATUS);
		modelAndView.setViewName("entities/orderStatus");
		return modelAndView;
	}

}

 