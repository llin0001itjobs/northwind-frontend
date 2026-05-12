package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.TypeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/typeState")
public class TypeStateController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles  {

    private final TypeStateService service;

    @Autowired
    public TypeStateController(TypeStateService service) { 
        this.service = service;
    }

	@GetMapping("/list")
	public ModelAndView getAllTypeStates() {
		handleRequest();
		modelAndView.addObject(TYPE_STATES, service.findAll());
		modelAndView.addObject(TITLE, TITLE_TYPE_STATE);
		modelAndView.setViewName("entities/typeState");
		return modelAndView;
	}
		

}	
