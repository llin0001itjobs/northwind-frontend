package org.llin.demo.northwind.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Employee;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController<T extends Employee> extends BaseController<T> {
	private static final String XSLT = "./xslt/employee.xslt";
	private static final String JSON = "./xslt/sample/employee.json";
	private Employee[] employees = {};

	@SuppressWarnings("unchecked")
	public EmployeeController() {			
		_type = (Class<T[]>) employees.getClass();					
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	@GetMapping("/list")
	public ModelAndView getAllEmployees() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("employee"));
		mv.addObject(TITLE, "Employee");
		return mv;		
	}
	
	@Override
	void loadConfigList() {						
		_configList.add("Company");			
		_configList.add("Privilege");
	}
	
}	
