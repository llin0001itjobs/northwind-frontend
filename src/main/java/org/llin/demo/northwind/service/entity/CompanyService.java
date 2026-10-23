package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CompanyService {

    private final RestClient restClient;

    @Autowired
    public CompanyService(@Qualifier("northwindDataClient") RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedCompanies {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public CompanyList companies;
    }

    private static class CompanyList {
        @com.fasterxml.jackson.annotation.JsonProperty("companies")
        public List<CompanyDto> companies;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /Company  (returns all Companies)
     */
    public List<CompanyDto> findAll() {
        EmbeddedCompanies response = restClient.get()
                .uri("company")
                .retrieve()
                .body(EmbeddedCompanies.class);

        List<CompanyDto> list = response != null 
                && response.companies != null 
                && response.companies.companies != null
                    ? response.companies.companies
                    : List.of();
        return enrichAll(list);
    }

    public List<CompanyDto> findById(Integer id) {
        if (id == null) return List.of();

        List<CompanyDto> list = new ArrayList<>();
        CompanyDto dto = restClient.get()
        .uri("company/{id}", id)
        .retrieve()
        .body(CompanyDto.class);
        list.add(dto);
        return enrichAll(list);        
    }
    
    public CompanyDto create(CompanyDto companyDto) {
        return restClient.post()
                .uri("company/{id}")
                .body(companyDto)
                .retrieve()
                .body(CompanyDto.class);
    }

    public CompanyDto update(Integer id, CompanyDto companyDto) {
        return restClient.put()
                .uri("company/{id}", id)
                .body(companyDto)
                .retrieve()
                .body(CompanyDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("company/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    private List<CompanyDto> enrichAll(List<CompanyDto> companies) {
		if (companies == null || companies.isEmpty()) {
			return List.of();
		}
		return companies.stream().map(this::withId).toList();
	}
	
	public CompanyDto withId(CompanyDto c) {
		if (c == null)
			return null;

		Integer id = extractIdFromSelfLink(c);

		return new CompanyDto(id, c.name(),c.links());
	}
		
	private Integer extractIdFromSelfLink(CompanyDto c) {
		if (c == null || c.links() == null)
			return null;
		var self = c.links().getLink("self");
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
	
	
}