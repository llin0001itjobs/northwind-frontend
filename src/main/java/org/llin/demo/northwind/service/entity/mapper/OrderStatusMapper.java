package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.OrderStatusDto;
import org.llin.demo.northwind.model.entity.OrderStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
	OrderStatusDto toDto(OrderStatus orderStatus);
	List<OrderStatusDto> toDtoList(List<OrderStatus> orderStatus);
	OrderStatus toEntity(OrderStatusDto orderStatusDto);
}

