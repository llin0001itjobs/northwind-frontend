package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.ModelViewCache;
import org.llin.demo.northwind.menu.EntityMenuManager;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class _BaseController<T extends BaseObject> implements _Values {

	private boolean isMenuLoaded = false;

	@Autowired
	ModelViewCache modelViewCache;

	@Autowired
	EntityMenuManager entityMapper;

	ModelAndView modelAndView = new ModelAndView();

	void handleRequest() {
		if (!isMenuLoaded) {
			modelAndView.addObject(MENU_FIRST_ORDER, entityMapper.getMappedEntities().getEntities().getFirstOrder());
			modelAndView.addObject(MENU_SECOND_ORDER, entityMapper.getMappedEntities().getEntities().getSecondOrder());
			modelAndView.addObject(MENU_THIRD_ORDER, entityMapper.getMappedEntities().getEntities().getThirdOrder());
			modelAndView.addObject(MENU_TYPE, entityMapper.getMappedEntities().getEntities().getType());
			isMenuLoaded = true;
		}
	}

}
