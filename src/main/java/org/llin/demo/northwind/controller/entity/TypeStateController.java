package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.TypeStateDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.TypeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/typeState")
public class TypeStateController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles  {

    private final TypeStateService service;

    @Autowired
    public TypeStateController(TypeStateService service) { 
        this.service = service;
    }

	@GetMapping("/list")
	public ModelAndView getAllTypeStates() {
		return createDefaultModelAndView();
	}
		
	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<TypeStateDto> opt = service.findById(id);

		if (opt.isPresent()) {
			mav.addObject(TYPE_STATE, opt.get());
			mav.setViewName("typeStates/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView()); 
		mv.addObject(TYPE_STATES, service.findAll());
		mv.addObject(TITLE, TITLE_TYPE_STATE);
		mv.setViewName("entities/typeState");
		return mv;		
	}

}	
