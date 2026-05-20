package org.llin.demo.northwind.model.entity.util;

import java.util.List;
import java.util.stream.Collectors;

import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.model.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }

        Role role = new Role();

        role.setId(dto.id());
        role.setDescription(dto.description());
        role.setType(dto.type());
        
        return role;
    }
    
    public static RoleDto toDto(Role entity) {
        if (entity == null) {
            return null;
        }

        return new RoleDto(
        		entity.getId(),
        		entity.getDescription(),
        		entity.getType()
        );
    }
    
    public static List<RoleDto> toDtos(List<Role> roles) {
        if (roles == null) {
            return null;                    // keep your original null policy
        }

        return roles.stream()
                    .map(RoleMapper::toDto)   // clean method reference
                    .collect(Collectors.toList());
    }
}
