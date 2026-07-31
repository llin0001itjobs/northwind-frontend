package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") 
public interface SupplierMapper {
	SupplierDto toDto(Supplier supplier);
	List<SupplierDto> toDtoList(List<Supplier> suppliers);
	Supplier toEntity(SupplierDto supplierDto);
}

