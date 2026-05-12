package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles  {

	private final ProductService productService;
	private final SupplierService supplierService;
	
	@Autowired
	public ProductController(ProductService productService,
			SupplierService supplierService) {
		this.productService = productService;
		this.supplierService = supplierService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllProducts() {
		handleRequest();
		modelAndView.addObject(PRODUCTS,productService.findAll());
		modelAndView.addObject(SUPPLIERS,supplierService.findAll());
		modelAndView.addObject(TITLE, TITLE_PRODUCT);
		modelAndView.setViewName("entities/product");
		return modelAndView;
	}

}	
