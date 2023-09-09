package org.llin.demo.northwind.controller.view;

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

	private OrderStatus orderStatus;	
	private Employee createdBy;
	private Supplier supplier;
	private Employee approvedBy;
	private Employee submittedBy;
	
	public PurchaseOrder() {
		super();
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Employee getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(Employee submittedBy) {
		this.submittedBy = submittedBy;
	}

	@Override
	public String toString() {
		return "\nPurchaseOrder [approvedDate=" + approvedDate + ", submittedDate=" + submittedDate
				+ ", creationDate=" + creationDate + ", expectedDate=" + expectedDate + ", shippingFee=" + shippingFee
				+ ", taxes=" + taxes + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", paymentMethod=" + paymentMethod + ", notes=" + notes + ", orderStatus=" + orderStatus
				+ ", createdBy=" + createdBy + ", supplier=" + supplier  + ", approvedBy=" + approvedBy
				+ ", submittedBy=" + submittedBy + super.toString() + "]";
	}

}
