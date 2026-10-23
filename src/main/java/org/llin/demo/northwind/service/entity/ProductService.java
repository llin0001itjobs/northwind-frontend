package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongValueDoubleDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.links.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

	private final RestClient restClient;

	@Autowired
	public ProductService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedProducts {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public ProductsList products;
	}

	private static class ProductsList {
		@com.fasterxml.jackson.annotation.JsonProperty("products")
		public List<ProductDto> products;
	}

	private static class EmbeddedSuppliers {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public SuppliersList suppliers;
	}

	private static class SuppliersList {
		@com.fasterxml.jackson.annotation.JsonProperty("suppliers")
		public List<SupplierDto> suppliers;
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
	 * GET product (returns all Products)
	 */
	public List<ProductDto> findAll() {
		EmbeddedProducts response = restClient.get().uri("product").retrieve().body(EmbeddedProducts.class);

		List<ProductDto> products = response != null && response.products != null && response.products.products != null
				? response.products.products
				: List.of();

		return enrichAll(products);
	}

	public Optional<ProductDto> findById(Integer id) {
		if (id == null) {
			return Optional.empty();
		}
		ProductDto product = restClient.get().uri("product/{id}", id).retrieve().body(ProductDto.class);

		return Optional.ofNullable(enrichWithSuppliers(product));
	}

	public ProductDto create(ProductDto ProductDto) {
		return restClient.post().uri("product").body(ProductDto).retrieve().body(ProductDto.class);
	}

	public ProductDto update(Integer id, ProductDto ProductDto) {
		return restClient.put().uri("product/{id}", id).body(ProductDto).retrieve().body(ProductDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("product/{id}", id).retrieve().toBodilessEntity();
	}

	public List<LabelValueLongValueDoubleDto> categoryRatios() {
		EmbeddedLabelValueLongValueDoubles response = restClient.get().uri("product/fee-range-count").retrieve()
				.body(EmbeddedLabelValueLongValueDoubles.class);
		return response != null && response.LabelValueLongValueDoubles != null
				&& response.LabelValueLongValueDoubles.LabelValueLongValueDouble != null
						? response.LabelValueLongValueDoubles.LabelValueLongValueDouble
						: List.of();
	}

	public List<LabelValueLongDto> priceRangePerListPrice() {
		EmbeddedLabelValueLongs response = restClient.get().uri("product/price-range-per-list-price").retrieve()
				.body(EmbeddedLabelValueLongs.class);
		return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();
	}

	public List<LabelValueLongDto> priceRangePerStandardCost() {
		EmbeddedLabelValueLongs response = restClient.get().uri("product/price-range-per-standard-cost").retrieve()
				.body(EmbeddedLabelValueLongs.class);
		return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();
	}

	public List<ProductDto> findByProductCode(String productCode) {
		return findByObject(productCode, "productCode", "findByProductCode");
	}

	public List<ProductDto> findByProductNameContaining(String productName) { // most useful
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
		return findWithTwoParameters(min, "min", max, "max", "findByStandardCostBetweenOrderByStandardCostAsc");
	}

	public List<ProductDto> findByListPriceBetweenOrderByListPriceAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByListPriceBetweenOrderByListPriceAsc");
	}

	public List<ProductDto> findByReorderLevelBetweenOrderByReorderLevelAsc(Integer min, Integer max) {
		return findWithTwoParameters(min, "min", max, "max", "findByReorderLevelBetweenOrderByReorderLevelAsc");
	}

	public List<ProductDto> findByTargetLevelBetweenOrderByTargetLevelAsc(Integer min, Integer max) {
		return findWithTwoParameters(min, "min", max, "max", "findByTargetLevelBetweenOrderByTargetLevelAsc");
	}

	public List<ProductDto> findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc(Integer min,
			Integer max) {
		return findWithTwoParameters(min, "min", max, "max",
				"findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc");
	}

	// Combined examples
	public List<ProductDto> findByCategoryAndDiscontinued(String category, Boolean discontinued) {
		return findWithTwoParameters(category, "category", discontinued, "discontinued",
				"findByCategoryAndDiscontinued");
	}

	private List<ProductDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}
		try {
			List<ProductDto> products = restClient.get().uri(uriBuilder -> uriBuilder.path("product/search/{method}")
					.queryParam(paramName, value).build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<ProductDto>>() {
					});

			return enrichAll(products);
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<ProductDto> findWithTwoParameters(Object param1, String paramName1, Object param2, String paramName2,
			String path) {
		try {
			List<ProductDto> products = restClient.get()
					.uri(uriBuilder -> uriBuilder.path("product/search/" + path).queryParam(paramName1, param1)
							.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<ProductDto>>() {
					});

			return enrichAll(products);
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<ProductDto> enrichAll(List<ProductDto> products) {
		if (products == null || products.isEmpty()) {
			return List.of();
		}
		return products.stream().map(this::enrichWithSuppliers).toList();
	}

	public ProductDto enrichWithSuppliers(ProductDto product) {
		if (product == null || product.links() == null) {
			return product;
		}

		Link suppliersLink = product.links().getLink("suppliers");
		if (suppliersLink == null || suppliersLink.getHref() == null) {
			return product;
		}

		try {
			EmbeddedSuppliers response = restClient.get().uri(suppliersLink.getHref()) // full URL from the link
					.retrieve().body(EmbeddedSuppliers.class);

			List<SupplierDto> list = (response != null && response.suppliers != null
					&& response.suppliers.suppliers != null) ? response.suppliers.suppliers : List.of();

			return withSuppliers(product, list);

		} catch (HttpClientErrorException e) {
			// link missing or 404 → leave suppliers empty
			return withSuppliers(product, List.of());
		}
	}

	// Helper used by your service / ObjectMapper post-processor
	public ProductDto withSuppliers(ProductDto dto, List<SupplierDto> suppliers) {
		return new ProductDto(dto.id(), suppliers, dto.productCode(), dto.productName(), dto.description(),
				dto.standardCost(), dto.listPrice(), dto.reorderLevel(), dto.targetLevel(), dto.quantityPerUnit(),
				dto.discontinued(), dto.minimumReorderQuantity(), dto.category(), dto.resourceId(), dto.links());
	}
		
	public ProductDto withId(ProductDto p) {
	    Integer id = extractIdFromSelfLink(p);
	    if (id == null) return p;
	    // adjust to match your ProductDto record constructor
	    return new ProductDto(id, p.suppliers(), p.productCode(), p.productName(),
	            p.description(), p.standardCost(), p.listPrice(), p.reorderLevel(),
	            p.targetLevel(), p.quantityPerUnit(), p.discontinued(),
	            p.minimumReorderQuantity(), p.category(), p.resourceId(), p.links());
	}
	
	public Integer extractIdFromSelfLink(ProductDto p) {
	    if (p == null || p.links() == null) return null;
	    var self = p.links().getLink("self");
	    if (self == null || self.getHref() == null) return null;
	    String href = self.getHref();
	    int slash = href.lastIndexOf('/');
	    if (slash < 0) return null;
	    try {
	        return Integer.valueOf(href.substring(slash + 1));
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}


}
