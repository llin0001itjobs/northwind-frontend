package org.llin.demo.northwind.controller.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.ShipperDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CompanyService;
import org.llin.demo.northwind.service.entity.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shipper")
public class ShipperController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {

	private final ShipperService shipperService;
	private final CompanyService companyService;

	@Autowired
	public ShipperController(ShipperService shipperService, CompanyService companyService) {
		this.shipperService = shipperService;
		this.companyService = companyService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllShippers() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<ShipperDto> shipperOpt = shipperService.findById(id);

		if (shipperOpt.isPresent()) {
			mav.addObject(SHIPPER, shipperOpt.get());
			mav.setViewName("suppliers/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
    @GetMapping("/search/findAllByLastName/{lastName}") 
    public ModelAndView findAllByLastName(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); 
        List<ShipperDto> shippers = shipperService.findAllByLastName(lastName);
        mav.addObject(SHIPPERS, shippers);
        return mav;    
    }

    @GetMapping("/search/findByLastNameContaining/{lastName}") 
    public ModelAndView findByLastNameContaining(@PathVariable String lastName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByLastNameContaining(lastName);
        mav.addObject(SHIPPERS, shippers);
        return mav; 
    }
    
    @GetMapping("/search/findByLastNameContaining/{firstName}") 
    public ModelAndView findByFirstNameContaining(@PathVariable String firstName) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByFirstNameContaining(firstName);
        mav.addObject(SHIPPERS, shippers);
        return mav;  
    }
        
    @GetMapping("/search/findByJobTitle/{jobTitle}") 
    public ModelAndView findByJobTitle(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByJobTitle(jobTitle);
        mav.addObject(SHIPPERS, shippers);
        return mav; 
    }
    
    @GetMapping("/search/findByJobTitleContaining/{jobTitle}") 
    public ModelAndView findByJobTitleContaining(@PathVariable String jobTitle) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByJobTitleContaining(jobTitle);
        mav.addObject(SHIPPERS, shippers);
        return mav;  
    }
    
    @GetMapping("/search/findByEmailAddress/{emailAddress}")
    public ModelAndView findByEmailAddress(@PathVariable String emailAddress) {
        ModelAndView mav = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByEmailAddress(emailAddress);
        mav.addObject(SHIPPERS, shippers);
        return mav;  
    }
    
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 		
		mv.addObject(SHIPPERS,shipperService.findAll());
		mv.addObject(COMPANIES,companyService.findAll());
		mv.addObject(TITLE, TITLE_SHIPPER);
		mv.setViewName("entities/shipper");
		return mv;
	}
}
