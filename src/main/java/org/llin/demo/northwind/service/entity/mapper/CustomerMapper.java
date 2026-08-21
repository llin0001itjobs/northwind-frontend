package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.CustomerDto;
import org.llin.demo.northwind.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring")
public interface CustomerMapper {
	CustomerDto toDto(Customer customer);
	List<CustomerDto> toDtoList(List<Customer> customer);
	Customer toEntity(CustomerDto customerDto);
}

