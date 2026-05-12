package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shipper")
public class ShipperController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final ShipperService shipperService;
	private final CompanyService companyService;

	@Autowired
	public ShipperController(ShipperService shipperService, CompanyService companyService) {
		this.shipperService = shipperService;
		this.companyService = companyService;
	}

	
	@GetMapping("/list")
	public ModelAndView getAllShippers() {
		handleRequest();
		modelAndView.addObject(SHIPPERS,shipperService.findAll());
		modelAndView.addObject(COMPANIES,companyService.findAll());
		modelAndView.addObject(TITLE, TITLE_SHIPPER);
		modelAndView.setViewName("entities/shipper");
		return modelAndView;
	}

}
