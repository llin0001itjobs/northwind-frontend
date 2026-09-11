package org.llin.demo.northwind.dto;

import java.util.List;

import org.llin.demo.northwind.dto.serializer.UserDtoDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = UserDtoDeserializer.class)
public record UserDto(
		int id,
		List<RoleDto> roles,
		String username,
		String password,
		String email,
		boolean enabled		
		
) {}
