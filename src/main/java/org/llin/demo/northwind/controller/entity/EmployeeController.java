package org.llin.demo.northwind.controller.entity;


import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	@GetMapping("/list")
	public ModelAndView getAllEmployees() {
		handleRequest();
		modelAndView.addObject(EMPLOYEES,modelViewCache.getObjectArray(EMPLOYEE));
		modelAndView.addObject(COMPANIES,modelViewCache.getObjectArray(COMPANY));
		modelAndView.addObject(PRIVILEGES,modelViewCache.getObjectArray(PRIVILEGE));
		modelAndView.addObject(TITLE, TITLE_EMPLOYEE);		
		modelAndView.setViewName("entities/employee");
		return modelAndView;
	}
	
}	
