package org.llin.demo.northwind.service.entity.mapper;

import java.util.List;

import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = _CentralConfig.class, componentModel = "spring", uses = {SupplierMapper.class})
public interface ProductMapper {

    @Mapping(source = "suppliers.suppliers", target = "suppliers")
    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    @Mapping(source = "suppliers", target = "suppliers.suppliers")
    Product toEntity(ProductDto productDto);

}