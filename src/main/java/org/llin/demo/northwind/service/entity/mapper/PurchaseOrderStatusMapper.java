package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.PurchaseOrderStatusDto;
import org.llin.demo.northwind.model.entity.PurchaseOrderStatus;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring") 
public interface PurchaseOrderStatusMapper {
	PurchaseOrderStatusDto toDto(PurchaseOrderStatus purchaseOrderStatus);
	List<PurchaseOrderStatusDto> toDtoList(List<PurchaseOrderStatus> purchaseOrderStatuss);
	PurchaseOrderStatus toEntity(PurchaseOrderStatusDto purchaseOrderStatusDto);	
}




