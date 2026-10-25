package org.llin.demo.northwind.controller.entity;

import java.util.List;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.OrderTaxStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/orderTaxStatus")
public class OrderTaxStatusController<T extends _EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

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
		ModelAndView mv = createDefaultModelAndView();
		List<OrderTaxStatusDto> list = service.findById(id);

		if (!list.isEmpty()) {
			mv.addObject(ORDER_TAX_STATUS, list.get(0));
			mv.setViewName("orderTaxStatuses/detail");
		} else {
			mv.setViewName("error/404");
		}

		return mv;
	}

	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(ORDER_TAX_STATUSES, service.findAll());
		mv.addObject("activeMenuSection", "type");
		mv.addObject("currentEntityPath", "orderTaxStatus");
		mv.addObject(TITLE, TITLE_ORDER_TAX_STATUS);
		mv.setViewName("entity/orderTaxStatus");
		return mv;
	}

}
