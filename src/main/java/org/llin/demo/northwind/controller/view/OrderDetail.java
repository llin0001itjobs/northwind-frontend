package org.llin.demo.northwind.controller.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDetail extends BaseObject {

	private double quantity;

	private double unitPrice;

	private double discount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAllocated;

	private InventoryTransaction inventoryTransaction;
	private OrderStatus orderStatus;
	private Product product;
	private PurchaseOrder purchaseOrder;
	private CustomerOrder customerOrder;
	
	public OrderDetail() {
		super();
	}
	
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

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	@Override
	public String toString() {
		return "\nOrderDetail [quantity=" + quantity + ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", dateAllocated=" + dateAllocated + ", inventoryTransaction=" + inventoryTransaction
				+ ", orderStatus=" + orderStatus + ", product=" + product + ", purchaseOrder=" + purchaseOrder
				+ ", customerOrder=" + customerOrder + super.toString() + "]";
	}







}
