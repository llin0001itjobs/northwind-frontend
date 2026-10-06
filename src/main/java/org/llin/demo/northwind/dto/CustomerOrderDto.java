package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record CustomerOrderDto(
		 int id,

		 EmployeeDto employee,
		 CustomerDto customer,

		 LocalDateTime orderDate,
		 LocalDateTime shippedDate,

		 ShipperDto shipper,
		 String shipName,
		 String shipAddress,
		 String shipCity,
		 String shipStateProvince,
		 String shipZipPostalCode,
		 String shipCountryRegion,

		 double shippingFee,
		 double taxes,	
		 String paymentType,
		 LocalDateTime paidDate,
		 String notes,
		 double taxRate,

		 OrderTaxStatusDto orderTaxStatus,
		 OrderStatusDto orderStatus,
         @JsonProperty("_links")
         @JsonDeserialize(using = LinksDeserializer.class) 
         Links links
		
) {}
