package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.llin.demo.northwind.model.entity.OrderTaxStatus;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring")
public interface OrderTaxStatusMapper {
	OrderTaxStatusDto toDto(OrderTaxStatus orderTaxStatus);
	List<OrderTaxStatusDto> toDtoList(List<OrderTaxStatus> orderTaxStatus);
	OrderTaxStatus toEntity(OrderTaxStatusDto orderTaxStatusDto);
}
