package org.llin.demo.northwind.controller.util;

import java.io.InputStream;

import org.llin.demo.northwind.controller.model.DisplayEntitiesContainer;
import org.llin.demo.northwind.controller.model.DisplayEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DisplayEntitiesMapper {
	private final static String DISPLAY_FILE = "DisplayEntity.json";  
	
    @SuppressWarnings("unchecked")
	public DisplayEntitiesContainer<DisplayEntity> mapEntitiesFromResource() {
    	DisplayEntitiesContainer<DisplayEntity> entities = new DisplayEntitiesContainer<>();
        try {
            InputStream inputStream = DisplayEntitiesMapper.class.getResourceAsStream(DISPLAY_FILE);
            ObjectMapper objectMapper = new ObjectMapper();
            entities = objectMapper.readValue(inputStream, DisplayEntitiesContainer.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public static void main(String[] args) {
        DisplayEntitiesMapper entityMapper = new DisplayEntitiesMapper();
        DisplayEntitiesContainer<DisplayEntity> mappedEntities = entityMapper.mapEntitiesFromResource();

        if (mappedEntities != null) {
            System.out.println(mappedEntities.toString());
        } else {
            System.out.println("Failed to load and map " + DISPLAY_FILE);
        }
    }
}
