package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.StatusAndTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/statusAndType")
public class StatusAndTypeController<T extends _EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	@Autowired
	private StatusAndTypeService statusAndTypeService;
	
	@GetMapping("/list")
	public ModelAndView getAllStatusAndType() {
		return createDefaultModelAndView();
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());		
		mv.addObject(INVENTORY_TRANSACTION_TYPES, statusAndTypeService.getIttService().findAll());
		mv.addObject(ORDER_DETAIL_STATUSES, statusAndTypeService.getOdsService().findAll());
		mv.addObject(ORDER_STATUSES, statusAndTypeService.getOsService().findAll());
		mv.addObject(ORDER_TAX_STATUSES, statusAndTypeService.getOtsService().findAll());
		mv.addObject(PURCHASE_ORDER_STATUSES, statusAndTypeService.getPosService().findAll());
		mv.addObject(PAYMENT_TYPES, statusAndTypeService.getPtService().findAll());
		mv.addObject(TITLE, TITLE_TYPE_STATUS_AND_TYPE);
		mv.addObject("activeMenuSection", "type");
		mv.addObject("currentEntityPath", "statusAndType");		 
		mv.setViewName("entity/statusAndType");
		return mv;		
	}	
	
}
