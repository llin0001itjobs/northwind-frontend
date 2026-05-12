package org.llin.demo.northwind.controller.entity;


import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {

	private final EmployeeService employeeService;
	private final CompanyService companyService;	
	
	@Autowired
	public EmployeeController(EmployeeService employeeService,
			CompanyService companyService) {
		this.employeeService = employeeService;
		this.companyService = companyService;		
	}
	
	@GetMapping("/list")
	public ModelAndView getAllEmployees() {
		handleRequest();
		modelAndView.addObject(EMPLOYEES,employeeService.findAll());
		modelAndView.addObject(COMPANIES,companyService.findAll());
		modelAndView.addObject(TITLE, TITLE_EMPLOYEE);		
		modelAndView.setViewName("entities/employee");
		return modelAndView;
	}
	
}	
