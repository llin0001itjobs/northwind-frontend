package org.llin.demo.northwind.controller.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final EmployeeService employeeService;
	private final CompanyService companyService;

	@Autowired
	public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
		this.employeeService = employeeService;
		this.companyService = companyService;
	}

	@GetMapping("/list")
	public ModelAndView getAllEmployees() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<EmployeeDto> employeeOpt = employeeService.findById(id);

		if (employeeOpt.isPresent()) {
			mav.addObject(EMPLOYEE, employeeOpt.get());
			mav.setViewName("employees/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

    @GetMapping("/search/findAllByLastName/{lastName}") 
    public ModelAndView findAllByLastName(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); 
        List<EmployeeDto> employees = employeeService.findAllByLastName(lastName);
        mav.addObject(EMPLOYEES, employees);
        return mav;    
    }

    @GetMapping("/search/findByLastNameContaining/{lastName}") 
    public ModelAndView findByLastNameContaining(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<EmployeeDto> employees = employeeService.findByLastNameContaining(lastName);
        mav.addObject(EMPLOYEES, employees);
        return mav; 
    }
    
    @GetMapping("/search/findByLastNameContaining/{firstName}") 
    public ModelAndView findByFirstNameContaining(@PathVariable String firstName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<EmployeeDto> employees = employeeService.findByFirstNameContaining(firstName);
        mav.addObject(EMPLOYEES, employees);
        return mav;  
    }
        
    @GetMapping("/search/findByJobTitle/{jobTitle}") 
    public ModelAndView findByJobTitle(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<EmployeeDto> employees = employeeService.findByJobTitle(jobTitle);
        mav.addObject(EMPLOYEES, employees);
        return mav; 
    }
    
    @GetMapping("/search/findByJobTitleContaining/{jobTitle}") 
    public ModelAndView findByJobTitleContaining(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<EmployeeDto> employees = employeeService.findByJobTitleContaining(jobTitle);
        mav.addObject(EMPLOYEES, employees);
        return mav;  
    }
    
    @GetMapping("/search/findByEmailAddress/{emailAddress}")
    public ModelAndView findByEmailAddress(@PathVariable String emailAddress) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<EmployeeDto> employees = employeeService.findByEmailAddress(emailAddress);
        mav.addObject(EMPLOYEES, employees);
        return mav;  
    }
    
	private ModelAndView createDefaultModelAndView() {
		loadMenu(); // Assumes this updates state safely or is stateless

		// Create a NEW instance every time to ensure thread safety
		ModelAndView mav = new ModelAndView(getModelAndView().getView());
		mav.addObject(EMPLOYEES, employeeService.findAll());
		mav.addObject(COMPANIES, companyService.findAll());
		mav.setViewName("entities/employee");

		return mav;
	}

}
