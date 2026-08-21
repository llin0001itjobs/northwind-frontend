package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(		config = _CentralConfig.class,
		componentModel = "spring", 
				  uses = {ProductMapper.class})
public interface SupplierMapper {

    @Mapping(source = "products.products", target = "products")
    SupplierDto toDto(Supplier supplier);

    List<SupplierDto> toDtoList(List<Supplier> suppliers);

    @Mapping(source = "products", target = "products.products")
    Supplier toEntity(SupplierDto supplierDto);

}