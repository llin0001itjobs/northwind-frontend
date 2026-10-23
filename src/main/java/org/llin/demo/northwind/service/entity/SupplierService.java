package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.SupplierDto;
import org.llin.demo.northwind.model.entity.links.Link;
import org.llin.demo.northwind.model.entity.links.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class SupplierService {
	
	private final RestClient restClient;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	public SupplierService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedSuppliers {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public SupplierList suppliers;
	}

	private static class SupplierList {
		@com.fasterxml.jackson.annotation.JsonProperty("suppliers")
		public List<SupplierDto> suppliers;
	}

	private static class EmbeddedProducts {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public ProductsList products;
	}

	private static class ProductsList {
		@com.fasterxml.jackson.annotation.JsonProperty("products")
		public List<ProductDto> products;
	}

	// ==================================================================
	// Public methods
	// ==================================================================
	/**
	 * GET /Supplier (returns all Suppliers)
	 */
	public List<SupplierDto> findAll() {
		EmbeddedSuppliers response = restClient.get().uri("supplier").retrieve().body(EmbeddedSuppliers.class);

		List<SupplierDto> suppliers = response != null && response.suppliers != null
				&& response.suppliers.suppliers != null ? response.suppliers.suppliers : List.of();

		return enrichAll(suppliers);
	}

	public Optional<SupplierDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		SupplierDto supplier = restClient.get().uri("supplier/{id}", id).retrieve().body(SupplierDto.class);

		return Optional.ofNullable(enrichWithProducts(supplier));
	}

	public SupplierDto create(SupplierDto SupplierDto) {
		return restClient.post().uri("supplier").body(SupplierDto).retrieve().body(SupplierDto.class);
	}

	public SupplierDto update(Integer id, SupplierDto SupplierDto) {
		return restClient.put().uri("supplier/{id}", id).body(SupplierDto).retrieve().body(SupplierDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("supplier/{id}", id).retrieve().toBodilessEntity();
	}

	public List<SupplierDto> findAllByLastName(String lastName) {
		return findByObject(lastName, "lastName", "findAllByLastName");
	}

	public List<SupplierDto> findByJobTitle(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitle");
	}

	public List<SupplierDto> findByEmailAddress(String emailAddress) {
		return findByObject(emailAddress, "emailAddress", "findByEmailAddress");
	}

	public List<SupplierDto> findByLastNameContaining(String lastName) {
		return findByObject(lastName, "lastName", "findByLastNameContaining");
	}

	public List<SupplierDto> findByFirstNameContaining(String firstName) {
		return findByObject(firstName, "firstName", "findByFirstNameContaining");
	}

	public List<SupplierDto> findByJobTitleContaining(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitleContaining");
	}

	private List<SupplierDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			List<SupplierDto> suppliers = restClient.get().uri(uriBuilder -> uriBuilder.path("supplier/search/{method}")
					.queryParam(paramName, value).build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<SupplierDto>>() {
					});

			return enrichAll(suppliers);
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<SupplierDto> enrichAll(List<SupplierDto> suppliers) {
		if (suppliers == null || suppliers.isEmpty()) {
			return List.of();
		}
		return suppliers.stream().map(this::enrichWithProducts).toList();
	}

	public SupplierDto enrichWithProducts(SupplierDto supplier) {
		if (supplier == null || supplier.links() == null) {
			return supplier;
		}

		Link productsLink = supplier.links().getLink("products");
		if (productsLink == null || productsLink.getHref() == null) {
			return supplier;
		}

		try {
			EmbeddedProducts response = restClient.get().uri(productsLink.getHref()) // full URL from the link
					.retrieve().body(EmbeddedProducts.class);

			List<ProductDto> list = (response != null && response.products != null
					&& response.products.products != null) ? response.products.products : List.of();

			return withProducts(supplier, list);

		} catch (HttpClientErrorException e) {
			// link missing or 404 → leave suppliers empty
			return withProducts(supplier, List.of());
		}
	}

	// Helper used by your service / ObjectMapper post-processor
	public SupplierDto withProducts(SupplierDto dto, List<ProductDto> products) {
		SupplierDto supplier = new SupplierDto(dto.id(), dto.company(), products, dto.lastName(), dto.firstName(),
				dto.emailAddress(), dto.jobTitle(), dto.businessPhone(), dto.homePhone(), dto.mobilePhone(),
				dto.faxNumber(), dto.address1(), dto.address2(), dto.city(), dto.stateProvince(), dto.zipPostalCode(),
				dto.countryRegion(), dto.notes(), dto.webSiteTitle(), dto.webSiteUrl(), dto.portraitPath(),
				dto.portraitTitle(), dto.links());
		return supplier;
	}

	public SupplierDto withId(SupplierDto s) {
		if (s == null)
			return null;

		Integer id = extractIdFromSelfLink(s);
		Integer companyId = extractCompanyId(s);
		CompanyDto companyDto = null;
		if (companyId != null) {
			// 
			List<CompanyDto>list = companyService.findById(companyId);
			companyDto = list.size() > 0 ? list.get(0) : new CompanyDto(companyId,"", new Links());
		}

		return new SupplierDto(id, // use the extracted id
				companyDto, s.products(), s.lastName(), s.firstName(), s.emailAddress(), s.jobTitle(), s.businessPhone(),
				s.homePhone(), s.mobilePhone(), s.faxNumber(), s.address1(), s.address2(), s.city(), s.stateProvince(),
				s.zipPostalCode(), s.countryRegion(), s.notes(), s.webSiteTitle(), s.webSiteUrl(), s.portraitPath(),
				s.portraitTitle(), s.links());
	}

	public Integer extractIdFromSelfLink(SupplierDto s) {
		if (s == null || s.links() == null)
			return null;
		var self = s.links().getLink("self");
		if (self == null || self.getHref() == null)
			return null;
		String href = self.getHref();
		int slash = href.lastIndexOf('/');
		if (slash < 0)
			return null;
		try {
			return Integer.valueOf(href.substring(slash + 1));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public Integer extractCompanyId(SupplierDto s) {
		if (s == null || s.links() == null)
			return null;

		// Try the relation named "company"
		Link companyLink = s.links().getLink("company");
		if (companyLink == null)
			return null;

		String href = companyLink.getHref();
		if (href == null)
			return null;

		// Case 1: query param ?id=6
		String idParam = queryParam(href, "id");
		if (idParam != null) {
			try {
				return Integer.valueOf(idParam);
			} catch (NumberFormatException ignored) {
			}
		}

		// Case 2: path segment .../company/6
		int last = href.lastIndexOf('/');
		if (last >= 0) {
			try {
				return Integer.valueOf(href.substring(last + 1));
			} catch (NumberFormatException ignored) {
			}
		}
		return null;
	}

	private String queryParam(String href, String name) {
		int q = href.indexOf('?');
		if (q < 0)
			return null;
		for (String part : href.substring(q + 1).split("&")) {
			String[] pair = part.split("=", 2);
			if (pair.length == 2 && pair[0].equals(name))
				return pair[1];
		}
		return null;
	}
}
