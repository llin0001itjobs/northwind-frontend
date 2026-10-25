package org.llin.demo.northwind.controller.entity;

import java.util.List;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.ShipperDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entity/shipper")
public class ShipperController<T extends _EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {

	private final ShipperService shipperService;

	@Autowired
	public ShipperController(ShipperService shipperService) {
		this.shipperService = shipperService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllShippers() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<ShipperDto> list = shipperService.findById(id);

		if (!list.isEmpty()) {
			mv.addObject(SHIPPER, list.get(0));
			mv.setViewName("suppliers/detail");
		} else {
			mv.setViewName("error/404");
		}

		return mv;
	}
	
    @GetMapping("/search/findAllByLastName/{lastName}") 
    public ModelAndView findAllByLastName(@PathVariable String lastName) {
        ModelAndView mv = createDefaultModelAndView(); 
        List<ShipperDto> shippers = shipperService.findAllByLastName(lastName);
        mv.addObject(SHIPPERS, shippers);
        return mv;    
    }

    @GetMapping("/search/findByLastNameContaining/{lastName}") 
    public ModelAndView findByLastNameContaining(@PathVariable String lastName) {
        ModelAndView mv = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByLastNameContaining(lastName);
        mv.addObject(SHIPPERS, shippers);
        return mv; 
    }
    
    @GetMapping("/search/findByLastNameContaining/{firstName}") 
    public ModelAndView findByFirstNameContaining(@PathVariable String firstName) {
        ModelAndView mv = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByFirstNameContaining(firstName);
        mv.addObject(SHIPPERS, shippers);
        return mv;  
    }
        
    @GetMapping("/search/findByJobTitle/{jobTitle}") 
    public ModelAndView findByJobTitle(@PathVariable String jobTitle) {
        ModelAndView mv = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByJobTitle(jobTitle);
        mv.addObject(SHIPPERS, shippers);
        return mv; 
    }
    
    @GetMapping("/search/findByJobTitleContaining/{jobTitle}") 
    public ModelAndView findByJobTitleContaining(@PathVariable String jobTitle) {
        ModelAndView mv = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByJobTitleContaining(jobTitle);
        mv.addObject(SHIPPERS, shippers);
        return mv;  
    }
    
    @GetMapping("/search/findByEmailAddress/{emailAddress}")
    public ModelAndView findByEmailAddress(@PathVariable String emailAddress) {
        ModelAndView mv = createDefaultModelAndView(); // Fresh instance
        List<ShipperDto> shippers = shipperService.findByEmailAddress(emailAddress);
        mv.addObject(SHIPPERS, shippers);
        return mv;  
    }
    
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 		
		mv.addObject(SHIPPERS,enrichAll(shipperService.findAll()));
		mv.addObject("activeMenuSection", "firstOrder");
		mv.addObject("currentEntityPath", "shipper");
		mv.addObject(TITLE, TITLE_SHIPPER);
		mv.setViewName("entity/shipper");
		return mv;
	}
	
	private List<ShipperDto> enrichAll(List<ShipperDto> shippers) {
		if (shippers == null || shippers.isEmpty())
			return List.of();
		return shippers.stream().map(shipperService::withId)
								 .toList();
	}
	
}
