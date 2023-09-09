package org.llin.demo.northwind.controller.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Invoice extends BaseObject {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invoiceDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dueDate;

	private double tax;
	private double shipping;
	private double amountDue;

	private CustomerOrder customerOrder;
	
	public Invoice() {
		super();
	}
	
	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

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
		return "\nInvoice [invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", tax=" + tax + ", shipping="
				+ shipping + ", amountDue=" + amountDue + ", customerOrder=" + customerOrder + super.toString() + "]";
	}

}
