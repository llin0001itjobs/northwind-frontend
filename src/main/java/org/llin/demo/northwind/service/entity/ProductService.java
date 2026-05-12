package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

    private final RestClient restClient;

    @Autowired
    public ProductService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedProducts {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public ProductList Products;
    }

    private static class ProductList {
        @com.fasterxml.jackson.annotation.JsonProperty("product")
        public List<ProductDto> Product;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<ProductDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/product/{id}", id)
                        .retrieve()
                        .body(ProductDto.class)
        );
    }

    /**
     * GET /product  (returns all Products)
     */
    public List<ProductDto> findAll() {
        EmbeddedProducts response = restClient.get()
                .uri("/product")
                .retrieve()
                .body(EmbeddedProducts.class);

        return response != null 
                && response.Products != null 
                && response.Products.Product != null
                    ? response.Products.Product
                    : List.of();
    }

    public ProductDto create(ProductDto ProductDto) {
        return restClient.post()
                .uri("/product")
                .body(ProductDto)
                .retrieve()
                .body(ProductDto.class);
    }

    public ProductDto update(Integer id, ProductDto ProductDto) {
        return restClient.put()
                .uri("/product/{id}", id)
                .body(ProductDto)
                .retrieve()
                .body(ProductDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/product/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
