package org.llin.demo.northwind.menu;

import java.io.InputStream;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Sets up the data structure to display entities
 */
@Component
public class EntityMenuManager {

    private static final String DEFAULT_JSON_FILE = "EntityMenu.json";

    @Autowired
    private PropertiesConfig propertiesConfig;

    private static boolean filesRead = false;

    private MenuEntitiesContainer<MenuEntity> mappedEntities = new MenuEntitiesContainer<>();

    private String entitiesFile;
    private boolean addlistSubpath;

    public EntityMenuManager() {
        // Only set defaults - do NOT load here
        this.entitiesFile = DEFAULT_JSON_FILE;
        this.addlistSubpath = true;
    }

    @PostConstruct
    private void mapEntitiesFromResource() {
        if (filesRead) {
            return;
        }

        try {
            // Use properties if configured, otherwise default
            if (propertiesConfig != null) {
                String configuredFile = propertiesConfig.getEntitiesFileJson();
                if (configuredFile != null && !configuredFile.trim().isEmpty()) {
                    this.entitiesFile = configuredFile;
                }
                this.addlistSubpath = propertiesConfig.isEntitiesAddlistSubpath();
            }

            // Spring way - always works when file is in src/main/resources/
            Resource resource = new ClassPathResource(entitiesFile);
            if (!resource.exists()) {
                throw new IllegalStateException("EntityMenu.json not found at classpath:" + entitiesFile);
            }

            try (InputStream inputStream = resource.getInputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                mappedEntities = objectMapper.readValue(inputStream, MenuEntitiesContainer.class);
            }

            mappedEntities.setAddlistSubpath(addlistSubpath);
            mappedEntities.addListSubpathForAll();

            filesRead = true;

            System.out.println("✅ EntityMenu.json loaded successfully (" + mappedEntities.getEntities().getFirstOrder().size() + " first-order entities)");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load EntityMenu.json", e);
        }
    }

    public MenuEntitiesContainer<MenuEntity> getMappedEntities() {
        return mappedEntities;
    }

    // Optional: for testing / manual use
    public static void main(String[] args) {
        EntityMenuManager entityMapper = new EntityMenuManager();
        // Simulate PostConstruct
        entityMapper.mapEntitiesFromResource();

        MenuEntitiesContainer<MenuEntity> mapped = entityMapper.getMappedEntities();
        System.out.println("mappedEntities: " + mapped);
        System.out.println("FirstOrder count: " + mapped.getEntities().getFirstOrder().size());
    }
}