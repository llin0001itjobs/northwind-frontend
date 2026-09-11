package org.llin.demo.northwind.dto.serializer;

import java.io.IOException;

import org.llin.demo.northwind.dto.RoleDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RoleDtoDeserializer extends JsonDeserializer<RoleDto> {

    @Override
    public RoleDto deserialize(JsonParser jp, DeserializationContext ctxt) 
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode node = mapper.readTree(jp);

        // 1. Safely extract primitive properties
        int id = node.has("id") ? node.get("id").asInt() : 0;
        String type = node.has("type") ? node.get("type").asText() : "";
        String description = node.has("description") ? node.get("description").asText() : "";

        // 2. Handle HAL links if the ID is nested inside a self reference link instead of a primitive field
        if (id == 0 && node.has("_links") && node.get("_links").has("self")) {
            String href = node.get("_links").get("self").get("href").asText();
            // Parse out the ID from the end of the URL string (e.g., /api/roles/3 -> 3)
            try {
                id = Integer.parseInt(href.substring(href.lastIndexOf("/") + 1));
            } catch (NumberFormatException e) {
                // Keep id as 0 if unparseable
            }
        }

        return new RoleDto(id, type, description);
    }
}
