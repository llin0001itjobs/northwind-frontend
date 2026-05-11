package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

public record PurchaseOrderDto(
		int id,

		EmployeeDto approvedBy,
		EmployeeDto createdBy,
		EmployeeDto submittedBy,
		SupplierDto supplier,
		OrderStatusDto orderStatus,

		LocalDateTime approvedDate,
		LocalDateTime submittedDate,
		LocalDateTime creationDate,
		LocalDateTime expectedDate,

		double shippingFee,
		double taxes,
		LocalDateTime paymentDate,
		double paymentAmount,
		String paymentMethod,

		String notes		
) {}
