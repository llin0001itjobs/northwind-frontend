package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles  {

	@GetMapping("/list")
	public ModelAndView getAllProducts() {
		handleRequest();
		modelAndView.addObject(PRODUCTS,modelViewCache.getObjectArray(PRODUCT));
		modelAndView.addObject(SUPPLIERS,modelViewCache.getObjectArray(SUPPLIER));
		modelAndView.addObject(TITLE, TITLE_PRODUCT);
		modelAndView.setViewName("entities/product");
		return modelAndView;
	}

}	
