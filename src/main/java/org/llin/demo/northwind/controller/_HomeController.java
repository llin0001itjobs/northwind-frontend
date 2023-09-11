package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.model.MenuEntitiesContainer;
import org.llin.demo.northwind.controller.model.MenuEntity;
import org.llin.demo.northwind.controller.util.EntityMenuMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class _HomeController {

	private static final String CONTAINER = "CONTAINER";
	
    @GetMapping("home/main")
    public ModelAndView main(Model model) {
		ModelAndView modelAndView = new ModelAndView("home");	
        EntityMenuMapper entityMapper = new EntityMenuMapper();
        MenuEntitiesContainer<MenuEntity> mappedEntities = entityMapper.mapEntitiesFromResource();
        
        modelAndView.addObject(CONTAINER, mappedEntities);
        
		return modelAndView; 
    }
}
