package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.OrderDetailDto;
import org.llin.demo.northwind.model.entity.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(		config = _CentralConfig.class, 
		componentModel = "spring",
				uses = {
						CustomerOrderMapper.class,
						InventoryTransactionMapper.class,
						OrderStatusMapper.class,
						ProductMapper.class,      
						PurchaseOrderMapper.class
				    })
public interface OrderDetailMapper {
	OrderDetailDto toDto(OrderDetail orderDetail);
	List<OrderDetailDto> toDtoList(List<OrderDetail> orderDetail);
	OrderDetail toEntity(OrderDetailDto orderDetailDto);
}

