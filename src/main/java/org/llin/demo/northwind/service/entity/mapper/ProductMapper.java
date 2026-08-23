

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
public abstract class ProductMapper {

    @Autowired
    @Lazy
    protected SupplierMapper supplierMapper;

    // ------------------------------------------------------------------
    // Main mapping methods
    // ------------------------------------------------------------------
    @Mapping(source = "suppliers.suppliers", target = "suppliers")
    public abstract ProductDto toDto(Product product);

    public abstract List<ProductDto> toDtoList(List<Product> products);

    @Mapping(source = "suppliers", target = "suppliers.suppliers")
    public abstract Product toEntity(ProductDto productDto);

    // Convenience method used by the helpers
    public abstract List<Product> toEntityList(List<ProductDto> list);

    // ------------------------------------------------------------------
    // Helpers for Suppliers ↔ List<SupplierDto>
    // ------------------------------------------------------------------
    protected List<SupplierDto> map(Suppliers suppliers) {
        if (suppliers == null || suppliers.getSuppliers() == null) {
            return null;
        }
        return supplierMapper.toDtoList(Arrays.asList(suppliers.getSuppliers()));
    }

    protected Suppliers mapSuppliers(List<SupplierDto> list) {
        if (list == null) {
            return null;
        }
        Suppliers s = new Suppliers();
        s.setSuppliers(supplierMapper.toEntityList(list).toArray(new Supplier[0]));
        return s;
    }

    // ------------------------------------------------------------------
    // Helpers for Products ↔ List<ProductDto>
    // (needed because of the deep nesting the other way around)
    // ------------------------------------------------------------------
    protected List<ProductDto> map(Products products) {
        if (products == null || products.getProducts() == null) {
            return null;
        }
        // 'this' is the current ProductMapper
        return this.toDtoList(Arrays.asList(products.getProducts()));
    }

    protected Products mapProducts(List<ProductDto> list) {
        if (list == null) {
            return null;
        }
        Products p = new Products();
        p.setProducts(this.toEntityList(list).toArray(new Product[0]));
        return p;
    }
}