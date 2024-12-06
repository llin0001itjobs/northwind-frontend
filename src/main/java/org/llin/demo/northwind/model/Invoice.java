package org.llin.demo.northwind.model;

import java.time.LocalDateTime;

import org.llin.demo.northwind._Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class Invoice extends BaseObject  implements _Classes {
			
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invoiceDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dueDate;

	private double tax;
	private double shipping;
	private double amountDue;
		
	private CustomerOrder customerOrder;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", tax=" + tax + ", shipping="
				+ shipping + ", amountDue=" + amountDue + ", customerOrder=" + customerOrder + super.toString() + "]";
	}
	
}
