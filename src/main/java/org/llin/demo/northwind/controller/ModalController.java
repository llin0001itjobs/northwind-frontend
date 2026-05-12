package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modal")
public class ModalController<T extends EntityObject> implements _Values {

	@RequestMapping("/show")
	public ModelAndView show(@RequestParam String entity, @RequestParam(name = "id") String csvId) {
		ModelAndView modelAndView = new ModelAndView("modal/" + getPath(entity,csvId));					
		System.out.println(ENTITY + " " + modelAndView.getModel().get(ENTITY));
		return modelAndView;
	}

	private String getPath(String entity, String id) {			
		return entity == null ? entity : entity + "?id="+ id;
	}
	
	
}
	

