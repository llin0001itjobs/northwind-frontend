package org.llin.demo.northwind.controller.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InventoryTransaction extends BaseObject {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionCreatedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionModifiedDate;

	private double quantity;
	private String comments;

	private Product product;
	private InventoryTransactionType inventoryTransactionType;
	private CustomerOrder customerOrder;
	private PurchaseOrder purchaseOrder;
	
	public InventoryTransaction() {
		super();	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public InventoryTransactionType getInventoryTransactionType() {
		return inventoryTransactionType;
	}

	public void setInventoryTransactionType(InventoryTransactionType inventoryTransactionType) {
		this.inventoryTransactionType = inventoryTransactionType;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "\nInventoryTransaction [transactionCreatedDate=" + transactionCreatedDate
				+ ", transactionModifiedDate=" + transactionModifiedDate + ", quantity=" + quantity + ", comments="
				+ comments + ", product=" + product + ", inventoryTransactionType="
				+ inventoryTransactionType + ", customerOrder=" + customerOrder + ", purchaseOrder=" + purchaseOrder
				+ super.toString() + "]";
	}
	
}
