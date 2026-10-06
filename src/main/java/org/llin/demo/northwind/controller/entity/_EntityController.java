package org.llin.demo.northwind.controller.entity;

import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.menu.EntityMenuManager;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.springframework.web.servlet.ModelAndView;

public abstract class _EntityController<T extends _EntityObject> implements _Values {

	public static final String ACTIVE_NAV_ITEM = "ACTIVE_NAV_ITEM";

	ModelAndView modelAndView = new ModelAndView();

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

	public void loadMenu() {
		EntityMenuManager entityMapper = new EntityMenuManager(); 
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-entities");
		modelAndView.addObject(MENU_FIRST_ORDER, entityMapper.getMappedEntities().getEntities().getFirstOrder());
		modelAndView.addObject(MENU_SECOND_ORDER, entityMapper.getMappedEntities().getEntities().getSecondOrder());
		modelAndView.addObject(MENU_THIRD_ORDER, entityMapper.getMappedEntities().getEntities().getThirdOrder());
		modelAndView.addObject(MENU_TYPE, entityMapper.getMappedEntities().getEntities().getType());
	}

}
