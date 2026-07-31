package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.PurchaseOrderStatusDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.PurchaseOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/purchaseOrderStatus")
public class PurchaseOrderStatusController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final PurchaseOrderStatusService service;
	
    @Autowired
    public PurchaseOrderStatusController(PurchaseOrderStatusService service) { 
        this.service = service;
    }
	
	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderStatuses() {		
		return createDefaultModelAndView();
	}                         

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<PurchaseOrderStatusDto> opt = service.findById(id);

		if (opt.isPresent()) {
			mav.addObject(PURCHASE_ORDER_STATUS, opt.get());
			mav.setViewName("purchaseOrderStatuss/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(PURCHASE_ORDER_STATUSES,service.findAll());
		mv.addObject(TITLE, TITLE_PURCHASE_ORDER_STATUS);
		mv.setViewName("entities/role");
		return mv;		
	}

}

