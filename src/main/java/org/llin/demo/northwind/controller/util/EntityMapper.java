package org.llin.demo.northwind.controller.util;

import java.io.InputStream;

import org.llin.demo.northwind.controller.model.EntitiesContainer;
import org.llin.demo.northwind.controller.model.Entity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityMapper {
	private final static String ENTITIES_FILE = "entities.json";  
	
    @SuppressWarnings("unchecked")
	public EntitiesContainer<Entity> mapEntitiesFromResource() {
    	EntitiesContainer<Entity> entities = new EntitiesContainer<>();
        try {
            InputStream inputStream = EntityMapper.class.getResourceAsStream(ENTITIES_FILE);
            ObjectMapper objectMapper = new ObjectMapper();
            entities = objectMapper.readValue(inputStream, EntitiesContainer.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public static void main(String[] args) {
        EntityMapper entityMapper = new EntityMapper();
        EntitiesContainer<Entity> mappedEntities = entityMapper.mapEntitiesFromResource();

        if (mappedEntities != null) {
            System.out.println(mappedEntities.toString());
        } else {
            System.out.println("Failed to load and map " + ENTITIES_FILE);
        }
    }
}
