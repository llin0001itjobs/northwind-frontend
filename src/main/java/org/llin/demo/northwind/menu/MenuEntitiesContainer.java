package org.llin.demo.northwind.menu;

import java.util.List;

public class MenuEntitiesContainer<T extends MenuEntity> {
	private static final String PATH_ENTITIES = "/entity/";
	private static final String LIST = "/list";
	
	private MenuEntities<T> entities = new MenuEntities<>();
	private boolean addlistSubpath;
	
	public MenuEntities<T> getEntities() {
		return entities;
	}

	public void setEntities(MenuEntities<T> entities) {
		this.entities = entities;
	}

	public boolean isAddlistSubpath() {
		return addlistSubpath;
	}

	public void setAddlistSubpath(boolean addlistSubpath) {
		this.addlistSubpath = addlistSubpath;
	}
	
	public void addListSubpathForAll() {
	    prefix(entities.getFirstOrder());
	    prefix(entities.getSecondOrder());
	    prefix(entities.getThirdOrder());
	    prefix(entities.getType());
	}

	private void prefix(List<? extends MenuEntity> list) {
	    for (MenuEntity me : list) {
	        me.setAddListSubpath(addlistSubpath);
	        if (!addlistSubpath || me.getPath() == null) {
	            continue;
	        }
	        String path = me.getPath();
	        if (!path.startsWith(PATH_ENTITIES)) {
	            path = PATH_ENTITIES + path;
	        }
	        if (!path.endsWith(LIST)) {
	            path = path + LIST;
	        }
	        me.setPath(path);
	    }
	}
	
	@Override
	public String toString() {
		return "MenuEntitiesContainer [entities=" + entities + "]";
	}

}
