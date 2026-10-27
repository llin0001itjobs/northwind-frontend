package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.ShipperDto;
import org.llin.demo.northwind.model.entity.links.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class ShipperService extends _BaseService {

	private final RestClient restClient;

	@Autowired
	private CompanyService companyService;

	@Autowired
	public ShipperService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedShippers {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public ShipperList Shippers;
	}

	private static class ShipperList {
		@com.fasterxml.jackson.annotation.JsonProperty("shippers")
		public List<ShipperDto> Shipper;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	/**
	 * GET /Shipper (returns all Shippers)
	 */
	public List<ShipperDto> findAll() {
		EmbeddedShippers response = restClient.get().uri("shipper").retrieve().body(EmbeddedShippers.class);

		return response != null && response.Shippers != null && response.Shippers.Shipper != null
				? response.Shippers.Shipper
				: List.of();
	}

	public List<ShipperDto> findById(Integer id) {
		if (id == null)
			return List.of();

		List<ShipperDto> list = new ArrayList<>();

		ShipperDto dto = restClient.get().uri("shipper/{id}", id).retrieve().body(ShipperDto.class);

		if (dto != null)
			list.add(dto);

		return list;
	}

	public ShipperDto create(ShipperDto ShipperDto) {
		return restClient.post().uri("shipper").body(ShipperDto).retrieve().body(ShipperDto.class);
	}

	public ShipperDto update(Integer id, ShipperDto ShipperDto) {
		return restClient.put().uri("shipper/{id}", id).body(ShipperDto).retrieve().body(ShipperDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("shipper/{id}", id).retrieve().toBodilessEntity();
	}

	public List<ShipperDto> findAllByLastName(String lastName) {
		return findByObject(lastName, "lastName", "findAllByLastName");
	}

	public List<ShipperDto> findByJobTitle(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitle");
	}

	public List<ShipperDto> findByEmailAddress(String emailAddress) {
		return findByObject(emailAddress, "emailAddress", "findByEmailAddress");
	}

	public List<ShipperDto> findByLastNameContaining(String lastName) {
		return findByObject(lastName, "lastName", "findByLastNameContaining");
	}

	public List<ShipperDto> findByFirstNameContaining(String firstName) {
		return findByObject(firstName, "firstName", "findByFirstNameContaining");
	}

	public List<ShipperDto> findByJobTitleContaining(String jobTitle) {
		return findByObject(jobTitle, "jobTitle", "findByJobTitleContaining");
	}

	private List<ShipperDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get().uri(uriBuilder -> uriBuilder.path("shipper/search/{method}")
					.queryParam(paramName, value).build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<ShipperDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return new ArrayList<>();
		}
	}

	public ShipperDto withId(ShipperDto s) {
		if (s == null)
			return null;

		Integer id = extractIdFromSelfLink(s);
		Integer companyId = extractCompanyId(s);
		CompanyDto companyDto = null;
		if (companyId != null) {
			Optional<CompanyDto> cOpt = companyService.findById(companyId);
			companyDto = cOpt.isPresent() ? cOpt.get() : new CompanyDto(companyId, "", new Links());
		}

		return new ShipperDto(id, // use the extracted id
				companyDto, s.lastName(), s.firstName(), s.emailAddress(), s.jobTitle(), s.businessPhone(),
				s.homePhone(), s.mobilePhone(), s.faxNumber(), s.address1(), s.address2(), s.city(), s.stateProvince(),
				s.zipPostalCode(), s.countryRegion(), s.notes(), s.webSiteTitle(), s.webSiteUrl(), s.portraitPath(),
				s.portraitTitle(), s.links());
	}

	private Integer extractCompanyId(ShipperDto s) {
		if (s == null || s.links() == null)
			return null;
		return extractIdFromLink(s.links().getLink("company"));
	}

	private Integer extractIdFromSelfLink(ShipperDto s) {
		if (s == null || s.links() == null)
			return null;
		return extractIdFromSelf(s.links().getLink("self"));
	}
}
