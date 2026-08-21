package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(config = _CentralConfig.class, componentModel = "spring")
public interface EmployeeMapper {
	EmployeeDto toDto(Employee employee);
	List<EmployeeDto> toDtoList(List<Employee> employee);
	Employee toEntity(EmployeeDto employeeDto);
}

