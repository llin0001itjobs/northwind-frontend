package org.llin.demo.northwind.dto;

import java.time.LocalDate;

import org.llin.demo.northwind.model.entity.links.Links;
import org.llin.demo.northwind.model.entity.links.LinksDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record CustomerOrderDto(
		 int id,

		 EmployeeDto employee,
		 CustomerDto customer,
		 ShipperDto shipper,
		 
		 LocalDate orderDate,
		 LocalDate shippedDate,
		 
		 String shipName,
		 String shipAddress,
		 String shipCity,
		 String shipStateProvince,
		 String shipZipPostalCode,
		 String shipCountryRegion,

		 double shippingFee,
		 double taxes,	
		 PaymentTypeDto paymentType,
		 LocalDate paidDate,
		 String notes,
		 double taxRate,

		 OrderTaxStatusDto orderTaxStatus,
		 OrderStatusDto orderStatus,
         @JsonProperty("_links")
         @JsonDeserialize(using = LinksDeserializer.class) 
         Links links
		
) {}
