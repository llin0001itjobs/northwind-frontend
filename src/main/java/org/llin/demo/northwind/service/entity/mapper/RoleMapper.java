package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring") 
public interface RoleMapper {
	RoleDto toDto(Role role);
	List<RoleDto> toDtoList(List<Role> roles);
	Role toEntity(RoleDto roleDto);
}

