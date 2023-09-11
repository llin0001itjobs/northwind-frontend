package org.llin.demo.northwind.controller.util;

import java.io.InputStream;

import org.llin.demo.northwind.controller.model.MenuEntitiesContainer;
import org.llin.demo.northwind.controller.model.MenuEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityMenuMapper {
	private final static String ENTITIES_FILE = "EntityMenu.json";  
	
    @SuppressWarnings("unchecked")
	public MenuEntitiesContainer<MenuEntity> mapEntitiesFromResource() {
    	MenuEntitiesContainer<MenuEntity> entities = new MenuEntitiesContainer<>();
        try {
            InputStream inputStream = EntityMenuMapper.class.getResourceAsStream(ENTITIES_FILE);
            ObjectMapper objectMapper = new ObjectMapper();
            entities = objectMapper.readValue(inputStream, MenuEntitiesContainer.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public static void main(String[] args) {
        EntityMenuMapper entityMapper = new EntityMenuMapper();
        MenuEntitiesContainer<MenuEntity> mappedEntities = entityMapper.mapEntitiesFromResource();

        if (mappedEntities != null) {
            System.out.println(mappedEntities.toString());
        } else {
            System.out.println("Failed to load and map " + ENTITIES_FILE);
        }
    }
}
