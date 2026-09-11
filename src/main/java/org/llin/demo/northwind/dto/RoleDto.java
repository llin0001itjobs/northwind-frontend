package org.llin.demo.northwind.dto;

import org.llin.demo.northwind.dto.serializer.RoleDtoDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = RoleDtoDeserializer.class)
public record RoleDto(
		int id,		
		String description,
		String type
) {}
