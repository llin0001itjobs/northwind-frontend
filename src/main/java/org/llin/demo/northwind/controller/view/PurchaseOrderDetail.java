package org.llin.demo.northwind.controller.view;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseOrderDetail extends BaseObject {

	private int quantity;
	private double unitCost;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateReceived;

	private boolean postedToInventory;

	private PurchaseOrder purchaseOrder;
	private Product product;
	private InventoryTransaction inventoryTransaction;

	public PurchaseOrderDetail() {
		super();
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public LocalDateTime getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(LocalDateTime dateReceived) {
		this.dateReceived = dateReceived;
	}

	public boolean isPostedToInventory() {
		return postedToInventory;
	}

	public void setPostedToInventory(boolean postedToInventory) {
		this.postedToInventory = postedToInventory;
	}

	 public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public InventoryTransaction getInventoryTransaction() {
		return inventoryTransaction;
	}

	public void setInventoryTransaction(InventoryTransaction inventoryTransaction) {
		this.inventoryTransaction = inventoryTransaction;
	}

	@Override
	public String toString() {
		return "\nPurchaseOrderDetail [quantity=" + quantity + ", unitCost=" + unitCost + ", dateReceived=" + dateReceived
				+ ", postedToInventory=" + postedToInventory + ", purchaseOrder=" + purchaseOrder
				+ ", product="+ product + ", inventoryTransaction=" + inventoryTransaction + super.toString() + "]";
	}
	  
}
