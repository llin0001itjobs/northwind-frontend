package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.UserDto;

// adjust package as needed

import org.llin.demo.northwind.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(	config = _CentralConfig.class, 
	componentModel = "spring", 
			  uses = {RoleMapper.class}) 
public interface UserMapper {
	
	UserDto toDto(User user);   
	List<UserDto> toDtoList(List<User> users);	
    User toEntity(UserDto userDto);
    
}
