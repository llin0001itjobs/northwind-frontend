package org.llin.demo.northwind.model.api.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InventoryTransaction extends BaseObject {
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionCreatedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionModifiedDate;

	private double quantity;
	private String comments;
		
	private InventoryTransaction[] self = {};
		
	public InventoryTransaction() {
		super();
		_type = self.getClass();
	}
	
	public LocalDateTime getTransactionCreatedDate() {
		return transactionCreatedDate;
	}

	public void setTransactionCreatedDate(LocalDateTime transactionCreatedDate) {
		this.transactionCreatedDate = transactionCreatedDate;
	}

	public LocalDateTime getTransactionModifiedDate() {
		return transactionModifiedDate;
	}

	public void setTransactionModifiedDate(LocalDateTime transactionModifiedDate) {
		this.transactionModifiedDate = transactionModifiedDate;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "\nInventoryTransaction [transactionCreatedDate=" + transactionCreatedDate
				+ ", transactionModifiedDate=" + transactionModifiedDate + ", quantity=" + quantity + ", comments="
				+ comments + super.toString() + "]";
	}

}
