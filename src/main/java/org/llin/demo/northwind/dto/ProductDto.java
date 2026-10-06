package org.llin.demo.northwind.dto;

import java.util.List;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public record ProductDto( 
	int id,
	List<SupplierDto> suppliers,
	
	String productCode,
	String productName,
	String description,

	double standardCost,
	double listPrice,

	int reorderLevel,
	int targetLevel,

	String quantityPerUnit,
	boolean discontinued,
	int minimumReorderQuantity,
	String category,
	int resourceId,
	
    @JsonProperty("_links")
    @JsonDeserialize(using = LinksDeserializer.class) 
    Links links
) {}