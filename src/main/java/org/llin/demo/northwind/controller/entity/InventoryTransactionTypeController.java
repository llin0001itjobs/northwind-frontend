package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.InventoryTransactionTypeDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.InventoryTransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventoryTransactionType")
public class InventoryTransactionTypeController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {

	@Autowired
	private InventoryTransactionTypeService service;

	@Autowired
	public InventoryTransactionTypeController(InventoryTransactionTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		return createDefaultModelAndView();
	}
	
	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<InventoryTransactionTypeDto> opt = service.findById(id);

		if (opt .isPresent()) {
			mav.addObject(INVENTORY_TRANSACTION_TYPE, opt.get());
			mav.setViewName("inventoryTransactionTypes/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(INVENTORY_TRANSACTION_TYPES,service.findAll());
		mv.addObject(TITLE, TITLE_INVENTORY_TRANSACTION_TYPE);
		mv.setViewName("entities/inventoryTransactionType");
		return mv;		
	}

}

