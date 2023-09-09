package org.llin.demo.northwind.model.api.core;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseOrder extends BaseObject {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approvedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime submittedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expectedDate;

	private double shippingFee;

	private double taxes;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paymentDate;

	private double paymentAmount;

	private String paymentMethod;

	private String notes;
	
	private PurchaseOrder[] self = {};
	
	public PurchaseOrder() {
		super();
		_type = self.getClass();
		label = "PurchaseOrder"; 
	}
	
	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}

	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(LocalDateTime expectedDate) {
		this.expectedDate = expectedDate;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "\nPurchaseOrder [approvedDate=" + approvedDate + ", submittedDate=" + submittedDate
				+ ", creationDate=" + creationDate + ", expectedDate=" + expectedDate + ", shippingFee=" + shippingFee
				+ ", taxes=" + taxes + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", paymentMethod=" + paymentMethod + ", notes=" + notes + super.toString() + "]";
	}

}
