package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") 
public interface ProductMapper {
	ProductDto toDto(Product product);
	List<ProductDto> toDtoList(List<Product> products);
	Product toEntity(ProductDto productDto);
}
