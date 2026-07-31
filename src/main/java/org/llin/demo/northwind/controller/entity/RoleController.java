package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
		
	@Autowired
	private final RoleService service;
	
    @Autowired
    public RoleController(RoleService service) { 
        this.service = service;
    }
    
	@GetMapping("/list")
	public ModelAndView getAllRoles() {
		return createDefaultModelAndView();
	}

	@GetMapping("/{type}")
	public ModelAndView findById(@PathVariable String type) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<RoleDto> opt = service.findByRoleType(type);

		if (opt.isPresent()) {
			mav.addObject(ROLE, opt.get());
			mav.setViewName("roles/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(ROLES,service.findAll());
		mv.addObject(TITLE, TITLE_ROLE);
		mv.setViewName("entities/role");
		return mv;		
	}
}	
