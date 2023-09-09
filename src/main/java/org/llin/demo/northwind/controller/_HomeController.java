package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.controller.model.EntitiesContainer;
import org.llin.demo.northwind.controller.model.Entity;
import org.llin.demo.northwind.controller.util.EntityMapper;
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
        EntityMapper entityMapper = new EntityMapper();
        EntitiesContainer<Entity> mappedEntities = entityMapper.mapEntitiesFromResource();
        
        modelAndView.addObject(CONTAINER, mappedEntities);
        
		return modelAndView; 
    }
}
