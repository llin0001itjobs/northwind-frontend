package org.llin.demo.northwind.dto;

import java.util.List;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record SupplierDto (
	    int id,

	    // Nested DTO for the OneToOne relationship
	    // (if the REST response embeds the full Company object)
	    CompanyDto company,
		List<ProductDto> products,
		
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
) {
    public SupplierDto {
        if (products == null) {
        	products = List.of();
        }
    }	
}

