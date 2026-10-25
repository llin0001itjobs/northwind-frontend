package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.CustomerDto;
import org.llin.demo.northwind.model.entity.links.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class CustomerService extends _BaseService {

	private final RestClient restClient;

	@Autowired
	private CompanyService companyService;

	@Autowired
	public CustomerService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class EmbeddedCustomers {
		@JsonProperty("_embedded")
		public CustomerList customers;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class CustomerList {
		@JsonProperty("customers")
		public List<CustomerDto> customer;
	}
	// ==================================================================
	// Public methods
	// ==================================================================

	public List<CustomerDto> findById(Integer id) {
		if (id == null)
			return List.of();

		List<CustomerDto> list = new ArrayList<>();

		CustomerDto dto = restClient.get().uri("customer/{id}", id).retrieve().body(CustomerDto.class);

		if (dto != null)
			list.add(dto);

		return list;
	}

	/**
	 * GET /customer (returns all customers)
	 * 
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public List<CustomerDto> findAll() {
		EmbeddedCustomers response = restClient.get().uri("customer").retrieve().body(EmbeddedCustomers.class);

		List<CustomerDto> customers = response != null && response.customers != null
				&& response.customers.customer != null ? response.customers.customer : List.of();

		return customers;
	}

	public CustomerDto create(CustomerDto customerDto) {
		return restClient.post().uri("customer").body(customerDto).retrieve().body(CustomerDto.class);
	}

	public CustomerDto update(Integer id, CustomerDto customerDto) {
		return restClient.put().uri("customer/{id}", id).body(customerDto).retrieve().body(CustomerDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("customer/{id}", id).retrieve().toBodilessEntity();
	}

	public List<CustomerDto> findAllByLastName(String lastName) {
		return findByObject(lastName, "lastName", "findAllByLastName");
	}

	public List<CustomerDto> findByJobTitle(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitle");
	}

	public List<CustomerDto> findByEmailAddress(String emailAddress) {
		return findByObject(emailAddress, "emailAddress", "findByEmailAddress");
	}

	public List<CustomerDto> findByLastNameContaining(String lastName) { // LIKE '%value%'
		return findByObject(lastName, "lastName", "findByLastNameContaining");
	}

	public List<CustomerDto> findByFirstNameContaining(String firstName) {
		return findByObject(firstName, "firstName", "findByFirstNameContaining");
	}

	public List<CustomerDto> findByJobTitleContaining(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitleContaining");
	}

	private List<CustomerDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		List<CustomerDto> list = restClient.get().uri(uriBuilder -> uriBuilder.path("customer/search/{method}")
				.queryParam(paramName, value).build(searchMethod)).retrieve()
				.body(new ParameterizedTypeReference<List<CustomerDto>>() {
				});
		try {
			return list;
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	public CustomerDto withId(CustomerDto c) {
		if (c == null)
			return null;

		Integer id = extractIdFromSelfLink(c);
		Integer companyId = extractCompanyId(c);
		CompanyDto companyDto = null;
		if (companyId != null) {
			//
			List<CompanyDto> list = companyService.findById(companyId);
			companyDto = list.size() > 0 ? list.get(0) : new CompanyDto(companyId, "", new Links());
		}

		return new CustomerDto(id, // use the extracted id
				companyDto, c.lastName(), c.firstName(), c.emailAddress(), c.jobTitle(), c.businessPhone(),
				c.homePhone(), c.mobilePhone(), c.faxNumber(), c.address1(), c.address2(), c.city(), c.stateProvince(),
				c.zipPostalCode(), c.countryRegion(), c.notes(), c.webSiteTitle(), c.webSiteUrl(), c.portraitPath(),
				c.portraitTitle(), c.links());
	}

	private Integer extractCompanyId(CustomerDto c) {
		if (c == null || c.links() == null)
			return null;
		return extractIdFromLink(c.links().getLink("company"));
	}

	private Integer extractIdFromSelfLink(CustomerDto c) {
		if (c == null || c.links() == null)
			return null;
		return extractIdFromSelf(c.links().getLink("self"));
	}
}