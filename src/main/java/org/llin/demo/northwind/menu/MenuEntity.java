package org.llin.demo.northwind.menu;

public class MenuEntity {

	private String path;
	private String title;
	private boolean addListSubpath;
	
	public MenuEntity() {
		
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		if (addListSubpath) 
			path += "/list";
		
		this.path = path;
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isAddListSubpath() {
		return addListSubpath;
	}

	public void setAddListSubpath(boolean addListSubpath) {
		this.addListSubpath = addListSubpath;
	}
	
	@Override
	public String toString() {
		return "Entity [path=" + path + ", title=" + title + "]";
	}
	
}
