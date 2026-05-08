package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.EntityObjectCache;
import org.llin.demo.northwind.menu.EntityMenuManager;
import org.llin.demo.northwind.model.EntityObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EntityController<T extends EntityObject> implements _Values {

	public static final String ACTIVE_NAV_ITEM = "ACTIVE_NAV_ITEM";
	
	private boolean isMenuLoaded = false;

	@Autowired
	EntityObjectCache<T> modelViewCache;

	@Autowired
	EntityMenuManager entityMapper;

	ModelAndView modelAndView = new ModelAndView();

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

	public void handleRequest() {
		if (!isMenuLoaded) {
			modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-entities");
			modelAndView.addObject(MENU_FIRST_ORDER, entityMapper.getMappedEntities().getEntities().getFirstOrder());
			modelAndView.addObject(MENU_SECOND_ORDER, entityMapper.getMappedEntities().getEntities().getSecondOrder());
			modelAndView.addObject(MENU_THIRD_ORDER, entityMapper.getMappedEntities().getEntities().getThirdOrder());
			modelAndView.addObject(MENU_TYPE, entityMapper.getMappedEntities().getEntities().getType());
			isMenuLoaded = true;
		}
	}

}
