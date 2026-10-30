package org.llin.demo.northwind.controller.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/employee")
public class EmployeeController<T extends _EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public ModelAndView getAllEmployees() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		Optional<EmployeeDto> opt = employeeService.findById(id);

		if (opt.isPresent()) {
			mv.addObject(EMPLOYEE, opt.get());
			mv.setViewName("employees/detail");
		} else {
			mv.setViewName("error/404");
		}

		return mv;
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
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(EMPLOYEES, enrichAll(employeeService.findAll()));
		mv.addObject("activeMenuSection", "thirdOrder");
		mv.addObject("currentEntityPath", "employee");
		mv.setViewName("entity/employee");

		return mv;
	}

	private List<EmployeeDto> enrichAll(List<EmployeeDto> employees) {
		if (employees == null || employees.isEmpty())
			return List.of();
		return employees.stream().map(employeeService::enrich).toList();
	}
	
}
