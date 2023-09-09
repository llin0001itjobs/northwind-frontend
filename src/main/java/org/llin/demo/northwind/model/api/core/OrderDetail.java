package org.llin.demo.northwind.model.api.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDetail extends BaseObject {

	private double quantity;

	private double unitPrice;

	private double discount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAllocated;
	
	private OrderDetail[] self = {};
	
	public OrderDetail() {
		super();
		_type = self.getClass();
		label = "OrderDetail"; 
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

	@Override
	public String toString() {
		return "\nOrderDetail [quantity=" + quantity + ", unitPrice=" + unitPrice + ", discount=" + discount
				+ ", dateAllocated=" + dateAllocated + super.toString() + "]";
	}

}
