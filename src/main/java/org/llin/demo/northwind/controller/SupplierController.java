package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Supplier;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/supplier")
public class SupplierController<T extends Supplier> extends BaseController<T> {

	private static final String XSLT = "./xslt/supplier.xslt";
	private static final String JSON = "./xslt/sample/supplier.json";
	private Supplier[] suppliers = {};
	
	@SuppressWarnings("unchecked")
	public SupplierController() {
		_type = (Class<T[]>) suppliers.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));	
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}

	@GetMapping("/list")
	public ModelAndView getAllSuppliers() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("supplier"));
		mv.addObject(TITLE, "Supplier");
		return mv;
	}

	@Override
	void loadConfigList() {
		_configList.add("Company");
	}
}
