package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.OrderDetailStatusDto;
import org.llin.demo.northwind.model.entity.OrderDetailStatus;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring")
public interface OrderDetailStatusMapper {
	OrderDetailStatusDto toDto(OrderDetailStatus orderDetailStatus);
	List<OrderDetailStatusDto> toDtoList(List<OrderDetailStatus> orderDetailStatus);
	OrderDetailStatus toEntity(OrderDetailStatusDto orderDetailStatusDto);
}

