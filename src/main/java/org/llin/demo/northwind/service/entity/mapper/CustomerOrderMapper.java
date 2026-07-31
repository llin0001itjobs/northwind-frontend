package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.model.entity.CustomerOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {
	CustomerOrderDto toDto(CustomerOrder customerOrder);
	List<CustomerOrderDto> toDtoList(List<CustomerOrder> customerOrder);
	CustomerOrder toEntity(CustomerOrderDto customerOrderDto);
}

