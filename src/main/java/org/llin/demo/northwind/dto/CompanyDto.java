package org.llin.demo.northwind.dto;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record CompanyDto(
		int id, 
		String name,
	    @JsonProperty("_links")
	    @JsonDeserialize(using = LinksDeserializer.class) 
	    Links links
) {}