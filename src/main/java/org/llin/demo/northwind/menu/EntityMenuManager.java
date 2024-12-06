package org.llin.demo.northwind.menu;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sets up the data structure to display entities
 */
@Component
public class EntityMenuManager {

	private static final String JSON_FILE = "EntityMenu.json";
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	
	private static boolean filesRead = false;

	private MenuEntitiesContainer<MenuEntity> mappedEntities = new MenuEntitiesContainer<>();

	private String entitiesFile;
	
	private boolean addlistSubpath;
	
	public EntityMenuManager() {
		entitiesFile = JSON_FILE;
		addlistSubpath = true;
		if (propertiesConfig != null) {
			entitiesFile = propertiesConfig.getEntitiesFileJson();
			addlistSubpath = propertiesConfig.isEntitiesAddlistSubpath();
		}
		mapEntitiesFromResource();
	}
	
	public MenuEntitiesContainer<MenuEntity> getMappedEntities() {
		return mappedEntities;
	}

	public void setMappedEntities(MenuEntitiesContainer<MenuEntity> mappedEntities) {
		this.mappedEntities = mappedEntities;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void mapEntitiesFromResource() {
		try {
			if (!filesRead) {
				InputStream inputStream = EntityMenuManager.class.getResourceAsStream(entitiesFile);
				ObjectMapper objectMapper = new ObjectMapper();
				mappedEntities = objectMapper.readValue(inputStream, MenuEntitiesContainer.class);
				mappedEntities.setAddlistSubpath(addlistSubpath);
				mappedEntities.addListSubpathForAll();
				filesRead = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EntityMenuManager entityMapper = new EntityMenuManager();

		entityMapper.mapEntitiesFromResource();
		MenuEntitiesContainer<MenuEntity> mappedEntities = entityMapper.getMappedEntities();

		if (mappedEntities != null) {
			System.out.println("mappedEntities:" + mappedEntities);
			System.out.println("getEntities: " + mappedEntities.getEntities());
			System.out.println("FirstOrder: " + mappedEntities.getEntities().getFirstOrder());
			System.out.println("SecondOrder: " + mappedEntities.getEntities().getSecondOrder());
			System.out.println("ThirdOrder: " + mappedEntities.getEntities().getThirdOrder());
			System.out.println("Type: " + mappedEntities.getEntities().getType());
		} else {
			System.out.println("Failed to load and map " + entityMapper.getMappedEntities());
		}
	}

}