package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/supplier")
public class SupplierController<T extends EntityObject> extends EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final SupplierService supplierService;
	private final CompanyService companyService;
	private final ProductService productService;

	@Autowired
	public SupplierController(SupplierService supplierService, CompanyService companyService,
			ProductService productService) {
		this.supplierService = supplierService;
		this.companyService = companyService;
		this.productService = productService;
	}

	@GetMapping("/list")
	public ModelAndView getAllSuppliers() {
		handleRequest();
		modelAndView.addObject(SUPPLIERS, supplierService.findAll());
		modelAndView.addObject(COMPANIES, companyService.findAll());
		modelAndView.addObject(PRODUCTS, productService.findAll());
		modelAndView.addObject(TITLE, TITLE_SUPPLIER);
		modelAndView.setViewName("entities/supplier");
		return modelAndView;
	}

}
