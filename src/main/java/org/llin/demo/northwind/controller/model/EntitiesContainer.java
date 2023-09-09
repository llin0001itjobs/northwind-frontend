package org.llin.demo.northwind.controller.model;

public class EntitiesContainer<T extends Entity> {
	private Entities<T> entities = new Entities<>();

	public Entities<T> getEntities() {
		return entities;
	}

	public void setEntities(Entities<T> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "EntitiesContainer [entities=" + entities + "]";
	}
	
}
