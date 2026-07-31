package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.model.entity.InventoryTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryTransactionMapper {
	InventoryTransactionDto toDto(InventoryTransaction inventoryTransaction);
	List<InventoryTransactionDto> toDtoList(List<InventoryTransaction> inventoryTransaction);
	InventoryTransaction toEntity(InventoryTransactionDto inventoryTransactionDto);
}

