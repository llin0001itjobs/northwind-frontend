package org.llin.demo.northwind.model;

import java.time.LocalDateTime;

import org.llin.demo.northwind._Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class PurchaseOrderDetail extends BaseObject implements _Classes {
		
	private int quantity;
	private double unitCost;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateReceived;

	private boolean postedToInventory;
			
	private InventoryTransaction inventoryTransaction;
	
	private Product product;
	
	private PurchaseOrder purchaseOrder;

	
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

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
	
	public InventoryTransaction getInventoryTransaction() {
		return inventoryTransaction;
	}

	public void setInventoryTransaction(InventoryTransaction inventoryTransaction) {
		this.inventoryTransaction = inventoryTransaction;
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
		return "PurchaseOrderDetail [quantity=" + quantity + ", unitCost=" + unitCost + ", dateReceived=" + dateReceived
				+ ", postedToInventory=" + postedToInventory + ", inventoryTransaction=" + inventoryTransaction
				+ ", product=" + product + ", purchaseOrder=" + purchaseOrder  + super.toString() + "]";
	}

}
