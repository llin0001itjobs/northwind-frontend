package org.llin.demo.northwind.service;

import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EmployeeService {

    private final RestClient restClient;

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
                        .uri("/employee/{id}", id)
                        .retrieve()
                        .body(EmployeeDto.class)
        );
    }

    /**
     * GET /Employee  (returns all Employees)
     */
    public List<EmployeeDto> findAll() {
        EmbeddedEmployees response = restClient.get()
                .uri("/employee")
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
                .uri("/employee")
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public EmployeeDto update(Integer id, EmployeeDto EmployeeDto) {
        return restClient.put()
                .uri("/employee/{id}", id)
                .body(EmployeeDto)
                .retrieve()
                .body(EmployeeDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/employee/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
