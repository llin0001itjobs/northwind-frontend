package org.llin.demo.northwind.menu;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class EntityMenuModelAdvice {

    private final EntityMenuManager entityMenuManager;

    public EntityMenuModelAdvice(EntityMenuManager entityMenuManager) {
        this.entityMenuManager = entityMenuManager;
    }

    @ModelAttribute("mappedEntities")
    public MenuEntitiesContainer<MenuEntity> mappedEntities() {
        return entityMenuManager.getMappedEntities();
    }
}