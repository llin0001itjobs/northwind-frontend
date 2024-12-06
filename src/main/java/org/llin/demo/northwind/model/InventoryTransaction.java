package org.llin.demo.northwind.model;

import java.time.LocalDateTime;

import org.llin.demo.northwind._Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class InventoryTransaction extends BaseObject implements _Classes {
			
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionCreatedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionModifiedDate;

	private double quantity;
	private String comments;
	
	private CustomerOrder customerOrder;
	
	private InventoryTransactionType inventoryTransactionType;
	
	private Product product;
	
	private PurchaseOrder purchaseOrder;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getTransactionCreatedDate() {
		return transactionCreatedDate;
	}

	public void setTransactionCreatedDate(LocalDateTime transactionCreatedDate) {
		this.transactionCreatedDate = transactionCreatedDate;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
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

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public InventoryTransactionType getInventoryTransactionType() {
		return inventoryTransactionType;
	}

	public void setInventoryTransactionType(InventoryTransactionType inventoryTransactionType) {
		this.inventoryTransactionType = inventoryTransactionType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "InventoryTransaction [transactionCreatedDate=" + transactionCreatedDate + ", transactionModifiedDate="
				+ transactionModifiedDate + ", quantity=" + quantity + ", comments=" + comments + ", customerOrder="
				+ customerOrder + ", inventoryTransactionType=" + inventoryTransactionType + ", product=" + product
				+ ", purchaseOrder=" + purchaseOrder + super.toString() + "]";
		
	}

}
