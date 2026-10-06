package org.llin.demo.northwind.dto;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record EmployeeDto (
    int id,

    // Nested DTO for the OneToOne relationship
    // (if the REST response embeds the full Company object)
    CompanyDto company,

    String lastName,
    String firstName,
    String emailAddress,
    String jobTitle,
    String businessPhone,
    String homePhone,
    String mobilePhone,
    String faxNumber,

    String address1,
    String address2,
    String city,
    String stateProvince,
    String zipPostalCode,
    String countryRegion,

    String notes,

    String webSiteTitle,
    String webSiteUrl,

    String portraitPath,
    String portraitTitle,
    
    @JsonProperty("_links")
    @JsonDeserialize(using = LinksDeserializer.class) 
    Links links
) {}

