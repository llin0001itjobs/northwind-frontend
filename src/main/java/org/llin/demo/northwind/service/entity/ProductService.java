package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.util.Collections;
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

    /**
     * GET /api/product  (returns all Products)
     */
    public List<ProductDto> findAll() {
        EmbeddedProducts response = restClient.get()
                .uri("/api/product")
                .retrieve()
                .body(EmbeddedProducts.class);

        return response != null 
                && response.Products != null 
                && response.Products.Product != null
                    ? response.Products.Product
                    : List.of();
    }

    public Optional<ProductDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/product/{id}", id)
                        .retrieve()
                        .body(ProductDto.class)
        );
    }

    
    public ProductDto create(ProductDto ProductDto) {
        return restClient.post()
                .uri("/api/product")
                .body(ProductDto)
                .retrieve()
                .body(ProductDto.class);
    }

    public ProductDto update(Integer id, ProductDto ProductDto) {
        return restClient.put()
                .uri("/api/product/{id}", id)
                .body(ProductDto)
                .retrieve()
                .body(ProductDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/product/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
	public List<LabelValueLongValueDoubleDto> categoryRatios() {
		EmbeddedLabelValueLongValueDoubles response = restClient.get()
    			.uri("/api/product/fee-range-count").retrieve()
    			.body(EmbeddedLabelValueLongValueDoubles.class);
    	return response != null && response.LabelValueLongValueDoubles != null && response.LabelValueLongValueDoubles.LabelValueLongValueDouble != null
				? response.LabelValueLongValueDoubles.LabelValueLongValueDouble
				: List.of();		
    }
	
	public List<LabelValueLongDto> priceRangePerListPrice() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/api/product/price-range-per-list-price").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
	}

	public List<LabelValueLongDto> priceRangePerStandardCost() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("/api/product/price-range-per-standard-cost").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
	}
 
	public List<ProductDto> findByProductCode(String productCode) {
    	return findByObject(productCode, "productCode", "findByProductCode");
    }
    
	public List<ProductDto> findByProductNameContaining(String productName) {   // most useful    	
    	return findByObject(productName, "productName", "findByProductNameContaining");
    }
    
	public List<ProductDto> findByCategoryContaining(String category) {
    	return findByObject(category, "category", "findByCategoryContaining");
    }
    
	public List<ProductDto> findByDescriptionContaining(String description) {
    	return findByObject(description, "description", "findByDescriptionContaining");
    }

	public List<ProductDto> findByDiscontinued(Boolean discontinued) {
    	return findByObject(discontinued, "discontinued", "findByDiscontinued");
    }

    // Price / level ranges
	public List<ProductDto> findByStandardCostBetweenOrderByStandardCostAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", 
				"findByStandardCostBetweenOrderByStandardCostAsc");       	
    }
    
	public List<ProductDto> findByListPriceBetweenOrderByListPriceAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", 
				"findByListPriceBetweenOrderByListPriceAsc");    	
    }
    
	public List<ProductDto> findByReorderLevelBetweenOrderByReorderLevelAsc(Integer min, Integer max) {
    	return findWithTwoParameters(min, "min", max, "max", 
				"findByReorderLevelBetweenOrderByReorderLevelAsc");    	
    }
    
	public List<ProductDto> findByTargetLevelBetweenOrderByTargetLevelAsc(Integer min, Integer max) {
    	return findWithTwoParameters(min, "min", max, "max", 
				"findByTargetLevelBetweenOrderByTargetLevelAsc");    	
    }
    
	public List<ProductDto> findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc(Integer min, Integer max) {
    	return findWithTwoParameters(min, "min", max, "max", 
    								"findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc");
    }

    // Combined examples
	public List<ProductDto> findByCategoryAndDiscontinued(String category, Boolean discontinued) {
    	return findWithTwoParameters(category, "category", discontinued, "discontinued", "findByCategoryAndDiscontinued");
    }
    
    private List<ProductDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/product/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(ProductDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
    
    private List<ProductDto> findWithTwoParameters(Object param1, String paramName1, 
			 											 Object param2, String paramName2, String path) {
			return restClient.get()
			.uri(uriBuilder -> uriBuilder.path("/api/product/search/" + path)
			.queryParam(paramName1, param1)
			.queryParam(paramName2, param2)
			.build())
			.retrieve()
			.body(new org.springframework.core.ParameterizedTypeReference<List<ProductDto>>() {});    	
    }
    
}
