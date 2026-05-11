package org.llin.demo.northwind.dto;

import java.util.List;

public record UserDto(
		int id,
		List<RoleDto> roles,
		String username,
		String password,
		String email,
		boolean enabled		
		
) {}
