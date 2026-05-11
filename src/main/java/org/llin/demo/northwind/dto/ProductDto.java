package org.llin.demo.northwind.dto;

import java.util.List;


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
	int resourceId
) {}