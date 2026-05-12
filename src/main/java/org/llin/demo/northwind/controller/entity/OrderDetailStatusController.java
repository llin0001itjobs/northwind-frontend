package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.OrderDetailStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetailStatus")
public class OrderDetailStatusController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final OrderDetailStatusService service;

	@Autowired
	public OrderDetailStatusController(OrderDetailStatusService service) {
		this.service = service;
	}

	@GetMapping("/list")
	public ModelAndView getAllOrderDetailStatuses() {
		handleRequest();
		modelAndView.addObject(ORDER_DETAIL_STATUSES, service.findAll());
		modelAndView.addObject(TITLE, TITLE_ORDER_DETAIL_STATUS);
		modelAndView.setViewName("entities/orderDetailStatus");
		return modelAndView;
	}

}
