package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.llin.demo.northwind.model.entity.PurchaseOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
	PurchaseOrderDto toDto(PurchaseOrder purchaseOrder);
	List<PurchaseOrderDto> toDtoList(List<PurchaseOrder> purchaseOrder);
	PurchaseOrder toEntity(PurchaseOrderDto purchaseOrderDto);
}

