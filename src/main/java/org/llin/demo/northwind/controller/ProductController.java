package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Product;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController<T extends Product> extends BaseController<T> {
	private static final String XSLT = "./xslt/product.xslt";
	private static final String JSON = "./xslt/sample/product.json";
	private Product[] products = {};

	@SuppressWarnings("unchecked")
	public ProductController() {				
		_type = (Class<T[]>) products.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	@GetMapping("/list")
	public ModelAndView getAllProducts() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("product"));
		mv.addObject(TITLE, "Product");
		return mv;
	}
		
	@Override
	void loadConfigList() {						
		_configList.add("Supplier");					
	}
}	
