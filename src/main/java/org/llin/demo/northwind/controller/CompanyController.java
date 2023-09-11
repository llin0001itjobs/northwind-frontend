package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Company;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/company")
public class CompanyController<T extends Company> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/company.xslt";
	private static final String JSON = "./xslt/sample/company.json";
	private Company[] companies = {};
	
	@SuppressWarnings("unchecked")
	public CompanyController() {
		_type = (Class<T[]>) companies.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));	
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	
	@GetMapping("/list")
	public ModelAndView getAllCompanies() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("company"));
		mv.addObject(TITLE, "Company");
		return mv;
	}

	@Override
	void loadConfigList() {
		//do nothing
	}

}
