package org.llin.demo.northwind.controller;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.EntityObjectCache;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modal")
public class ModalController<T extends EntityObject> implements _Values {

	@Autowired
	EntityObjectCache<T> baseObjectCache;
	
	@RequestMapping("/show")
	public ModelAndView show(@RequestParam String entity, @RequestParam(name = "id") String csvId) {
		ModelAndView modelAndView = new ModelAndView("modal/" + getPath(entity,csvId));			
		modelAndView.addObject(ENTITY, getBaseObjects(entity, csvId));
		System.out.println(ENTITY + " " + modelAndView.getModel().get(ENTITY));
		return modelAndView;
	}

	private String getPath(String entity, String id) {			
		return entity == null ? entity : entity + "?id="+ id;
	}
	
	@SuppressWarnings("unchecked")
	private T[] getBaseObjects(String entity, String csvId) {
		List<T> list = new ArrayList<>();
		String[] ids = csvId.split(",");	
		for (String i: ids) {
			T bo = (T) baseObjectCache.getById(entity, Integer.valueOf(i));
			list.add(bo);
		}	
		return (T[]) list.toArray();
	}
	
}
	

