package org.llin.demo.northwind.dto;

import java.time.LocalDateTime;

public record InvoiceDto(
		int id,
		
		CustomerOrderDto customerOrder,
		
		LocalDateTime invoiceDate,
		LocalDateTime dueDate,

		double tax,
		double shipping,		
		double amountDue

) {}
