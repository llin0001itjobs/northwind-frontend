package org.llin.demo.northwind.menu;

public class MenuEntitiesContainer<T extends MenuEntity> {
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
		for (MenuEntity me : entities.getFirstOrder()) {
			me.setAddListSubpath(addlistSubpath);
			me.setPath(me.getPath());			
		}
		for (MenuEntity me : entities.getSecondOrder()) {
			me.setAddListSubpath(addlistSubpath);
			me.setPath(me.getPath());			
		}
		for (MenuEntity me : entities.getThirdOrder()) {
			me.setAddListSubpath(addlistSubpath);
			me.setPath(me.getPath());			
		}		
		for (MenuEntity me : entities.getType()) {
			me.setAddListSubpath(addlistSubpath);
			me.setPath(me.getPath());			
		}		
		
	}
	
	@Override
	public String toString() {
		return "MenuEntitiesContainer [entities=" + entities + "]";
	}


	
}
