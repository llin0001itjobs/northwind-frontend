package org.llin.demo.northwind.service;

import org.llin.demo.northwind.dto.CompanyDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CompanyService {

    private final RestClient restClient;

    public CompanyService(RestClient restClient) {
        this.restClient = restClient;
    }

    // ==================================================================
    // Helper classes for HAL _embedded wrapper (placed at class level)
    // ==================================================================

    private static class EmbeddedCompanys {
        @com.fasterxml.jackson.annotation.JsonProperty("_embedded")
        public CompanyList Companys;
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
        EmbeddedCompanys response = restClient.get()
                .uri("/company")
                .retrieve()
                .body(EmbeddedCompanys.class);

        return response != null 
                && response.Companys != null 
                && response.Companys.Company != null
                    ? response.Companys.Company
                    : List.of();
    }


}