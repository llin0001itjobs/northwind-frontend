package org.llin.demo.northwind.controller.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/supplier")
public class SupplierController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final SupplierService supplierService;
	private final CompanyService companyService;
	private final ProductService productService;

	@Autowired
	public SupplierController(SupplierService supplierService, CompanyService companyService,
			ProductService productService) {
		this.supplierService = supplierService;
		this.companyService = companyService;
		this.productService = productService;
	}

	@GetMapping("/list")
	public ModelAndView getAllSuppliers() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<SupplierDto> supplierOpt = supplierService.findById(id);

		if (supplierOpt.isPresent()) {
			mav.addObject(SUPPLIER, supplierOpt.get());
			mav.setViewName("suppliers/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
    @GetMapping("/search/findAllByLastName/{lastName}") 
    public ModelAndView findAllByLastName(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); 
        List<SupplierDto> suppliers = supplierService.findAllByLastName(lastName);
        mav.addObject(SUPPLIERS, suppliers);
        return mav;    
    }

    @GetMapping("/search/findByLastNameContaining/{lastName}") 
    public ModelAndView findByLastNameContaining(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<SupplierDto> suppliers = supplierService.findByLastNameContaining(lastName);
        mav.addObject(SUPPLIERS, suppliers);
        return mav; 
    }
    
    @GetMapping("/search/findByLastNameContaining/{firstName}") 
    public ModelAndView findByFirstNameContaining(@PathVariable String firstName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<SupplierDto> suppliers = supplierService.findByFirstNameContaining(firstName);
        mav.addObject(SUPPLIERS, suppliers);
        return mav;  
    }
        
    @GetMapping("/search/findByJobTitle/{jobTitle}") 
    public ModelAndView findByJobTitle(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<SupplierDto> suppliers = supplierService.findByJobTitle(jobTitle);
        mav.addObject(SUPPLIERS, suppliers);
        return mav; 
    }
    
    @GetMapping("/search/findByJobTitleContaining/{jobTitle}") 
    public ModelAndView findByJobTitleContaining(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<SupplierDto> suppliers = supplierService.findByJobTitleContaining(jobTitle);
        mav.addObject(SUPPLIERS, suppliers);
        return mav;  
    }
    
    @GetMapping("/search/findByEmailAddress/{emailAddress}")
    public ModelAndView findByEmailAddress(@PathVariable String emailAddress) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<SupplierDto> suppliers = supplierService.findByEmailAddress(emailAddress);
        mav.addObject(SUPPLIERS, suppliers);
        return mav;  
    }
    
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 	
		mv.addObject(SUPPLIERS, supplierService.findAll());
		mv.addObject(COMPANIES, companyService.findAll());
		mv.addObject(PRODUCTS, productService.findAll());
		mv.addObject(TITLE, TITLE_SUPPLIER);
		mv.setViewName("entities/supplier");
		return mv;		
	}
}
