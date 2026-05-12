package org.llin.demo.northwind.service.entity;

import org.llin.demo.northwind.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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
                .uri("/company")
                .retrieve()
                .body(EmbeddedCompanies.class);

        return response != null 
                && response.Companies != null 
                && response.Companies.Company != null
                    ? response.Companies.Company
                    : List.of();
    }


}