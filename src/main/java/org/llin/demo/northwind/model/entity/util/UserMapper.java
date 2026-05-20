package org.llin.demo.northwind.model.entity.util;

import java.util.List;
import java.util.stream.Collectors;

import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.dto.UserDto;

// adjust package as needed

import org.llin.demo.northwind.model.entity.User;

// Optional: if you have a RoleMapper, import it too
// import org.llin.demo.northwind.model.mapper.RoleMapper;

public class UserMapper {

    /**
     * Maps UserDto (record) to a new User entity.
     * All matching fields are copied.
     * Fields not present in the DTO (emailVerified, verificationToken, newPassword, confirmPassword)
     * keep their default values from the User class.
     * 
     * Note on roles:
     *   - UserDto contains List<RoleDto> roles (multiple roles possible)
     *   - User entity contains a single Role role
     *   - If you want to support roles, either:
     *       1. Add a RoleMapper and map the first RoleDto (or all roles if you change User to List<Role>)
     *       2. Or handle roles separately after mapping (recommended for flexibility)
     */
    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        // Core fields from DTO
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setEnabled(dto.enabled());

         user.setRoles(dto.roles().stream()
                 .map(RoleMapper::toEntity)
                 .collect(Collectors.toList()));

        return user;
    }

    /**
     * Optional reverse mapping (User → UserDto) if you also need it later.
     */
    public static UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }

        // Since User has only one role but DTO expects a List, we wrap the single role
        // (or return empty list if role is null)
        List<RoleDto> roleDtos = entity.getRoles() != null 
                ? RoleMapper.toDtos(entity.getRoles()) 
                : java.util.List.of();


        return new UserDto(
                entity.getId(),
                roleDtos,
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.isEnabled()
        );
    }
}