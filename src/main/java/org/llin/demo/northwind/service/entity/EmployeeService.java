package org.llin.demo.northwind.service.entity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EmployeeService {

    private final RestClient restClient;

    @Autowired
    public EmployeeService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedEmployees {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public EmployeeList Employees;
    }

    private static class EmployeeList {
        @com.fasterxml.jackson.annotation.JsonProperty("employee")
        public List<EmployeeDto> Employee;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    public Optional<EmployeeDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/employee/{id}", id)
                        .retrieve()
                        .body(EmployeeDto.class)
        );
    }

    /**
     * GET /Employee  (returns all Employees)
     */
    public List<EmployeeDto> findAll() {
        EmbeddedEmployees response = restClient.get()
                .uri("/api/employee")
                .retrieve()
                .body(EmbeddedEmployees.class);

        return response != null 
                && response.Employees != null 
                && response.Employees.Employee != null
                    ? response.Employees.Employee
                    : List.of();
    }

    public EmployeeDto create(EmployeeDto EmployeeDto) {
        return restClient.post()
                .uri("/api/employee")
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public EmployeeDto update(Integer id, EmployeeDto EmployeeDto) {
        return restClient.put()
                .uri("/api/employee/{id}", id)
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/employee/{id}", id)
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
	
    private List<EmployeeDto> findByObject(Object o, String label, String path) {
		if (o  == null) return Collections.emptyList();

		   return Optional.ofNullable(
		            restClient.get()
		                    .uri("/api/employee/search/" + path + "?" + label + "={" + label + "}", o)
		                    .retrieve()
		                    .body(EmployeeDto.class)
		            ) 
		            .map(Collections::singletonList)
		            .orElse(Collections.emptyList());
    }
    
}
