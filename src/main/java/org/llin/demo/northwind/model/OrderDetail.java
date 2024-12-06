package org.llin.demo.northwind.model;

import java.time.LocalDateTime;

import org.llin.demo.northwind._Classes;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDetail extends BaseObject implements _Classes {
	
	private double quantity;

	private double unitPrice;

	private double discount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAllocated;
	
	private CustomerOrder customerOrder;
	
	private InventoryTransaction inventoryTransaction;

	private OrderStatus orderStatus;
	
	private Product product;
	
	private PurchaseOrder purchaseOrder;
	
	
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public LocalDateTime getDateAllocated() {
		return dateAllocated;
	}

	public void setDateAllocated(LocalDateTime dateAllocated) {
		this.dateAllocated = dateAllocated;
	}
	
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public InventoryTransaction getInventoryTransaction() {
		return inventoryTransaction;
	}

	public void setInventoryTransaction(InventoryTransaction inventoryTransaction) {
		this.inventoryTransaction = inventoryTransaction;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
		return "OrderDetail [quantity=" + quantity + ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", dateAllocated=" + dateAllocated + ", customerOrder=" + customerOrder + ", inventoryTransaction="
				+ inventoryTransaction + ", orderStatus=" + orderStatus + ", product=" + product + ", purchaseOrder="
				+ purchaseOrder  + super.toString() + "]";
	}

}
