package org.llin.demo.northwind.dto.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.dto.UserDto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDtoDeserializer extends JsonDeserializer<UserDto> {

    @Override
    public UserDto deserialize(JsonParser jp, DeserializationContext ctxt) 
            throws IOException {
    	
    	System.out.println("*********BEGIN deserialize**************");
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode node = mapper.readTree(jp);

        int id = node.has("id") ? node.get("id").asInt() : 0;
        String username = node.has("username") ? node.get("username").asText() : "";
        String password = node.has("password") ? node.get("password").asText() : "";
        String email = node.has("email") ? node.get("email").asText() : "";
        boolean enabled = node.has("enabled") && node.get("enabled").asBoolean();

        List<RoleDto> roles = new ArrayList<>();
        
        // Handle incoming data if it contains a nested JSON array format
        if (node.has("roles") && node.get("roles").isArray()) {
            for (JsonNode roleNode : node.get("roles")) {
                roles.add(mapper.treeToValue(roleNode, RoleDto.class));
            }
        } 
        // Handle incoming data if it uses Spring Data REST HAL links format instead
        else if (node.has("_links") && node.get("_links").has("roles")) {
            JsonNode rolesLink = node.get("_links").get("roles");
            // You can optionally perform a secondary query here if you need to fetch full objects
            // For now, initializing an empty array ensures parser stability
        }

        return new UserDto(id, roles, username, password, email, enabled);
    }
}
