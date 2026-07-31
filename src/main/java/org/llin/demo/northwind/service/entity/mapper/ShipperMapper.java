package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.ShipperDto;
import org.llin.demo.northwind.model.entity.Shipper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") 
public interface ShipperMapper {
	ShipperDto toDto(Shipper shipper);
	List<ShipperDto> toDtoList(List<Shipper> shippers);
	Shipper toEntity(ShipperDto shipperDto);
}

