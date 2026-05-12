package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController<T extends EntityObject> extends EntityController<T> implements _Classes_EntityObject, _Titles {
		
	@Autowired
	private final RoleService service;
	
    @Autowired
    public RoleController(RoleService service) { 
        this.service = service;
    }
    
	@GetMapping("/list")
	public ModelAndView getAllRoles() {
		handleRequest();
		modelAndView.addObject(ROLES,service.findAll());
		modelAndView.addObject(TITLE, TITLE_ROLE);
		modelAndView.setViewName("entities/role");
		return modelAndView;
	}

}	
