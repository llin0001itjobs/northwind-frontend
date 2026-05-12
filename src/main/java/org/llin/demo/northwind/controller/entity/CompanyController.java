package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/company")
public class CompanyController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
				
	@Autowired
	private CompanyService service;
	
	@GetMapping("/list")
	public ModelAndView getAllCompanies() {
		handleRequest();
		modelAndView.addObject(COMPANIES,service.findAll());
		modelAndView.addObject(TITLE, TITLE_COMPANY);
		modelAndView.setViewName("entities/company");
		return modelAndView;
	}
	
}
