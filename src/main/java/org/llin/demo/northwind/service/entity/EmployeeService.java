package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.model.entity.links.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class EmployeeService extends _BaseService {

    private final RestClient restClient;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    public EmployeeService(@Qualifier("northwindDataClient") RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedEmployees {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public EmployeeList employees;
    }

    private static class EmployeeList {
        @com.fasterxml.jackson.annotation.JsonProperty("employees")
        public List<EmployeeDto> employees;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public List<EmployeeDto> findById(Integer id) {
        
		if (id == null)
			return List.of();

		List<EmployeeDto> list = new ArrayList<>();

		EmployeeDto dto = restClient.get().uri("employee/{id}", id).retrieve().body(EmployeeDto.class);

		if (dto != null)
			list.add(dto);

		return list;
    }

    /**
     * GET /employee  (returns all Employees)
     */
    public List<EmployeeDto> findAll() {
        EmbeddedEmployees response = restClient.get()
                .uri("employee")
                .retrieve()
                .body(EmbeddedEmployees.class);

        return response != null 
                && response.employees != null 
                && response.employees.employees != null
                    ? response.employees.employees
                    : List.of();
    }

    public EmployeeDto create(EmployeeDto EmployeeDto) {
        return restClient.post()
                .uri("employee")
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public EmployeeDto update(Integer id, EmployeeDto EmployeeDto) {
        return restClient.put()
                .uri("employee/{id}", id)
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("employee/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
    
    public List<EmployeeDto> findAllByLastName(String lastName) {
		return findByObject(lastName,"lastName","findAllByLastName");
	}
	
	public List<EmployeeDto> findByJobTitle(String jobTitle) {
		return findByObject(jobTitle,"jobTitle","findByJobTitle");
	}
	
	public List<EmployeeDto> findByEmailAddress(String emailAddress) {
		return findByObject(emailAddress,"emailAddress","findByEmailAddress");	
	}
	
	public List<EmployeeDto> findByLastNameContaining(String lastName) {        // LIKE '%value%'
		return findByObject(lastName,"lastName","findByLastNameContaining");		
	}
	
	public List<EmployeeDto> findByFirstNameContaining(String firstName) {
		return findByObject(firstName,"firstName","findByFirstNameContaining");		
	}
	
	public List<EmployeeDto> findByJobTitleContaining(String jobTitle) {
		return findByObject(jobTitle,"jobTitle","findByJobTitleContaining");
	}
	
	private List<EmployeeDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get().uri(uriBuilder -> uriBuilder
					.path("employee/search/{method}")
					.queryParam(paramName, value)
					.build(searchMethod)).retrieve()
					.body(new ParameterizedTypeReference<List<EmployeeDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	public EmployeeDto withId(EmployeeDto e) {
		if (e == null)
			return null;

		Integer id = extractIdFromSelfLink(e);
		Integer companyId = extractCompanyId(e);
		CompanyDto companyDto = null;
		if (companyId != null) {
			//
			List<CompanyDto> list = companyService.findById(companyId);
			companyDto = list.size() > 0 ? list.get(0) : new CompanyDto(companyId, "", new Links());
		}

		return new EmployeeDto(id, // use the extracted id
				companyDto, e.lastName(), e.firstName(), e.emailAddress(), e.jobTitle(), e.businessPhone(),
				e.homePhone(), e.mobilePhone(), e.faxNumber(), e.address1(), e.address2(), e.city(), e.stateProvince(),
				e.zipPostalCode(), e.countryRegion(), e.notes(), e.webSiteTitle(), e.webSiteUrl(), e.portraitPath(),
				e.portraitTitle(), e.links());
	}

	private Integer extractCompanyId(EmployeeDto e) {
		if (e == null || e.links() == null)
			return null;
		return extractIdFromLink(e.links().getLink("company"));
	}

	private Integer extractIdFromSelfLink(EmployeeDto e) {
		if (e == null || e.links() == null)
			return null;
		return extractIdFromSelf(e.links().getLink("self"));
	}
	
}
