package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final RestClient restClient;

    @Autowired
    public CompanyService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedCompanies {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public CompanyList Companies;
    }

    private static class CompanyList {
        @com.fasterxml.jackson.annotation.JsonProperty("company")
        public List<CompanyDto> Company;
    }

    // ==================================================================
    // Public methods
    // ==================================================================

    /**
     * GET /Company  (returns all Companies)
     */
    public List<CompanyDto> findAll() {
        EmbeddedCompanies response = restClient.get()
                .uri("/api/company")
                .retrieve()
                .body(EmbeddedCompanies.class);

        return response != null 
                && response.Companies != null 
                && response.Companies.Company != null
                    ? response.Companies.Company
                    : List.of();
    }

    public Optional<CompanyDto> findById(Integer id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(
                restClient.get()
                        .uri("/api/company/{id}", id)
                        .retrieve()
                        .body(CompanyDto.class)
        );
    }
    
    public CompanyDto create(CompanyDto companyDto) {
        return restClient.post()
                .uri("/api/company/{id}")
                .body(companyDto)
                .retrieve()
                .body(CompanyDto.class);
    }

    public CompanyDto update(Integer id, CompanyDto companyDto) {
        return restClient.put()
                .uri("/api/company/{id}", id)
                .body(companyDto)
                .retrieve()
                .body(CompanyDto.class);
    }

    public void deleteById(Integer id) {
        restClient.delete()
                .uri("/api/company/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}