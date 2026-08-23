package org.llin.demo.northwind.service.entity.mapper;

import java.util.Arrays;
import java.util.List;

import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.Product;
import org.llin.demo.northwind.model.entity.Products;
import org.llin.demo.northwind.model.entity.Supplier;
import org.llin.demo.northwind.model.entity.Suppliers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(
    config = _CentralConfig.class,
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class SupplierMapper {

    @Autowired
    @Lazy
    protected ProductMapper productMapper;

    // ------------------------------------------------------------------
    // Main mapping methods
    // ------------------------------------------------------------------
    @Mapping(source = "products.products", target = "products")
    public abstract SupplierDto toDto(Supplier supplier);

    public abstract List<SupplierDto> toDtoList(List<Supplier> suppliers);

    @Mapping(source = "products", target = "products.products")
    public abstract Supplier toEntity(SupplierDto supplierDto);

    // ------------------------------------------------------------------
    // Helpers for Products ↔ List<ProductDto>
    // ------------------------------------------------------------------
    protected List<ProductDto> map(Products products) {
        if (products == null || products.getProducts() == null) {
            return null;
        }
        return productMapper.toDtoList(Arrays.asList(products.getProducts()));
    }

    protected Products mapProducts(List<ProductDto> list) {
        if (list == null) {
            return null;
        }
        Products p = new Products();
        p.setProducts(productMapper.toEntityList(list).toArray(new Product[0]));
        return p;
    }

    // ------------------------------------------------------------------
    // Helpers for Suppliers ↔ List<SupplierDto>
    // (these are exactly what the compiler is asking for)
    // ------------------------------------------------------------------
    protected List<SupplierDto> map(Suppliers suppliers) {
        if (suppliers == null || suppliers.getSuppliers() == null) {
            return null;
        }
        // 'this' is the current SupplierMapper – safe because of @Lazy on the other side
        return this.toDtoList(Arrays.asList(suppliers.getSuppliers()));
    }

    protected Suppliers mapSuppliers(List<SupplierDto> list) {
        if (list == null) {
            return null;
        }
        Suppliers s = new Suppliers();
        s.setSuppliers(this.toEntityList(list).toArray(new Supplier[0]));
        return s;
    }

    // Optional convenience methods (MapStruct will use them if present)
    public abstract List<Supplier> toEntityList(List<SupplierDto> list);
}