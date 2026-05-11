package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

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
		 OrderStatusDto orderStatus
		
) {}
