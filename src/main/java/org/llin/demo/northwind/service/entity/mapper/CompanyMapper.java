package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	CompanyDto toDto(Company company);
	List<CompanyDto> toDtoList(List<Company> company);
	Company toEntity(CompanyDto companyDto);
}


