package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.llin.demo.northwind.model.entity.OrderTaxStatus;

public interface OrderTaxStatusMapper {
	OrderTaxStatusDto toDto(OrderTaxStatus orderTaxStatus);
	List<OrderTaxStatusDto> toDtoList(List<OrderTaxStatus> orderTaxStatus);
	OrderTaxStatus toEntity(OrderTaxStatusDto orderTaxStatusDto);
}
