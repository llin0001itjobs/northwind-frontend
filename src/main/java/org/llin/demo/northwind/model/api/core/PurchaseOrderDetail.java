package org.llin.demo.northwind.model.api.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseOrderDetail extends BaseObject {
    
	private int quantity;
	private double unitCost;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateReceived;

	private boolean postedToInventory;

	private PurchaseOrderDetail[] self = {};
	
	public PurchaseOrderDetail() {
		super();
		_type = self.getClass();
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
	
	@Override
	public String toString() {
		return "\nPurchaseOrderDetail [quantity=" + quantity + ", unitCost=" + unitCost + ", dateReceived=" + dateReceived
				+ ", postedToInventory=" + postedToInventory + super.toString() + "]";
	}
	  
}
