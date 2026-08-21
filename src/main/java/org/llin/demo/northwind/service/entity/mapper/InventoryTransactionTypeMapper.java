package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.InventoryTransactionTypeDto;
import org.llin.demo.northwind.model.entity.InventoryTransactionType;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring") 
public interface InventoryTransactionTypeMapper {
	InventoryTransactionTypeDto toDto(InventoryTransactionType inventoryTransactionType);
	List<InventoryTransactionTypeDto> toDtoList(List<InventoryTransactionType> inventoryTransactionTypes);
	InventoryTransactionType toEntity(InventoryTransactionTypeDto inventoryTransactionTypeDto);
}
