package org.llin.demo.northwind.controller.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.CustomerDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/customer")
public class CustomerController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
    private final CustomerService customerService;
	private final CompanyService companyService;
	
    @Autowired
    public CustomerController(CustomerService customerService, CompanyService companyService) { 
        this.customerService = customerService;
        this.companyService = companyService; 
    }

    @GetMapping("/list")
    public ModelAndView getAllCustomers() {
        return createDefaultModelAndView();
    }
    
    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {    	
        return ResponseEntity.ok(customerService.create(dto));
    }
    
    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto dto) {
        return ResponseEntity.ok(customerService.update(dto.id(), dto));
    }

    @DeleteMapping
    public ResponseEntity<CustomerDto> delete(@RequestBody CustomerDto dto) {
    	customerService.deleteById(dto.id());
        return ResponseEntity.ok(dto);
    }
     

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Integer id) {
    	ModelAndView mav = createDefaultModelAndView();
        Optional<CustomerDto> customerOpt = customerService.findById(id);
        
        if (customerOpt.isPresent()) {
            mav.addObject(CUSTOMER, customerOpt.get());
            mav.setViewName("customers/detail");
        } else {
            mav.setViewName("error/404");
        }
        
        return mav;      	
    }
    
    @GetMapping("/search/findAllByLastName/{lastName}") 
    public ModelAndView findAllByLastName(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); 
        List<CustomerDto> customers = customerService.findAllByLastName(lastName);
        mav.addObject(CUSTOMERS, customers);
        return mav;    
    }

    @GetMapping("/search/findByLastNameContaining/{lastName}") 
    public ModelAndView findByLastNameContaining(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<CustomerDto> customers = customerService.findByLastNameContaining(lastName);
        mav.addObject(CUSTOMERS, customers);
        return mav; 
    }
    
    @GetMapping("/search/findByLastNameContaining/{firstName}") 
    public ModelAndView findByFirstNameContaining(@PathVariable String firstName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<CustomerDto> customers = customerService.findByFirstNameContaining(firstName);
        mav.addObject(CUSTOMERS, customers);
        return mav;  
    }
        
    @GetMapping("/search/findByJobTitle/{jobTitle}") 
    public ModelAndView findByJobTitle(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<CustomerDto> customers = customerService.findByJobTitle(jobTitle);
        mav.addObject(CUSTOMERS, customers);
        return mav; 
    }
    
    @GetMapping("/search/findByJobTitleContaining/{jobTitle}") 
    public ModelAndView findByJobTitleContaining(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<CustomerDto> customers = customerService.findByJobTitleContaining(jobTitle);
        mav.addObject(CUSTOMERS, customers);
        return mav;  
    }
    
    @GetMapping("/search/findByEmailAddress/{emailAddress}")
    public ModelAndView findByEmailAddress(@PathVariable String emailAddress) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<CustomerDto> customers = customerService.findByEmailAddress(emailAddress);
        mav.addObject(CUSTOMERS, customers);
        return mav;  
    }
    
    private ModelAndView createDefaultModelAndView() {
        loadMenu(); // Assumes this updates state safely or is stateless
        
        // Create a NEW instance every time to ensure thread safety
        ModelAndView mav = new ModelAndView(getModelAndView().getView());		
        mav.addObject(CUSTOMERS, customerService.findAll());
        mav.addObject(COMPANIES, companyService.findAll());
        mav.setViewName("entities/customer");
        
        return mav;
    }  
	
}