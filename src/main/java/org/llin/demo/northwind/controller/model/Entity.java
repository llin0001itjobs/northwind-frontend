package org.llin.demo.northwind.controller.model;

public class Entity {
	private String path;
	private String title;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Entity [path=" + path + ", title=" + title + "]";
	}
	
}
