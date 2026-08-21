package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.TypeStateDto;
import org.llin.demo.northwind.model.entity.TypeState;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring") 
public interface TypeStateMapper {
	TypeStateDto toDto(TypeState typeState);
	List<TypeStateDto> toDtoList(List<TypeState> typeStates);
	TypeState toEntity(TypeStateDto typeStateDto);
}


