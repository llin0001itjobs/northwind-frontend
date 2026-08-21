package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.PurchaseOrderDetailDto;
import org.llin.demo.northwind.model.entity.PurchaseOrderDetail;
import org.mapstruct.Mapper;

@Mapper(		config = _CentralConfig.class, 
		componentModel = "spring",
				uses = {
						InventoryTransactionMapper.class,
						ProductMapper.class,      
						PurchaseOrderMapper.class
				    })

public interface PurchaseOrderDetailMapper {
	PurchaseOrderDetailDto toDto(PurchaseOrderDetail purchaseOrderDetail);    
	List<PurchaseOrderDetailDto> toDtoList(List<PurchaseOrderDetail> purchaseOrderDetail);
	PurchaseOrderDetail toEntity(PurchaseOrderDetailDto purchaseOrderDetailDto);
}
