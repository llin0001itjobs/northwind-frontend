package org.llin.demo.northwind.service.entity;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongValueDoubleDto;
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

 	private static class EmbeddedLabelValueLongValueDoubles {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongValueDoubleList LabelValueLongValueDoubles;
	}

	private static class LabelValueLongValueDoubleList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLongValueDouble")
		public List<LabelValueLongValueDoubleDto> LabelValueLongValueDouble;
	}
	
	private static class EmbeddedLabelValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongList LabelValueLongs;
	}

	private static class LabelValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLong")
		public List<LabelValueLongDto> LabelValueLong;
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
    
	public List<LabelValueLongValueDoubleDto> categoryRatios() {
		EmbeddedLabelValueLongValueDoubles response = restClient.get()
    			.uri("/product/analytics/fee-range-count").retrieve()
    			.body(EmbeddedLabelValueLongValueDoubles.class);
    	return response != null && response.LabelValueLongValueDoubles != null && response.LabelValueLongValueDoubles.LabelValueLongValueDouble != null
				? response.LabelValueLongValueDoubles.LabelValueLongValueDouble
				: List.of();		
    }
	
	public List<LabelValueLongDto> priceRangePerListPrice() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/product/analytics/price-range-per-list-price").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
	}

	public List<LabelValueLongDto> priceRangePerStandardCost() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/product/analytics/price-range-per-standard-cost").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
	}
	
}
