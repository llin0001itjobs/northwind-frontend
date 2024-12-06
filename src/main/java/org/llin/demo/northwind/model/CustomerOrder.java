package org.llin.demo.northwind.model;

import java.time.LocalDateTime;

import org.llin.demo.northwind._Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class CustomerOrder extends BaseObject implements _Classes {
			     	
	private String shipName;

	private String shipAddress;

	private String shipCity;

	private String shipStateProvince;

	private String shipZipPostalCode;
	
	private String shipCountryRegion;

	private double shippingFee;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime shippedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime orderDate;

	private double taxes;
	
	private String paymentType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paidDate;

	private String notes;

	private double taxRate;

	private Customer customer;
	
	private Employee employee;
	
	private Shipper shipper;
	
	
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipStateProvince() {
		return shipStateProvince;
	}

	public void setShipStateProvince(String shipStateProvince) {
		this.shipStateProvince = shipStateProvince;
	}

	public String getShipZipPostalCode() {
		return shipZipPostalCode;
	}

	public void setShipZipPostalCode(String shipZipPostalCode) {
		this.shipZipPostalCode = shipZipPostalCode;
	}

	public String getShipCountryRegion() {
		return shipCountryRegion;
	}

	public void setShipCountryRegion(String shipCountryRegion) {
		this.shipCountryRegion = shipCountryRegion;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDateTime shippedDate) {
		this.shippedDate = shippedDate;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDateTime paidDate) {
		this.paidDate = paidDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	@Override
	public String toString() {
		return "CustomerOrder [shipName=" + shipName + ", shipAddress=" + shipAddress + ", shipCity=" + shipCity
				+ ", shipStateProvince=" + shipStateProvince + ", shipZipPostalCode=" + shipZipPostalCode
				+ ", shipCountryRegion=" + shipCountryRegion + ", shippingFee=" + shippingFee + ", shippedDate="
				+ shippedDate + ", orderDate=" + orderDate + ", taxes=" + taxes + ", paymentType=" + paymentType
				+ ", paidDate=" + paidDate + ", notes=" + notes + ", taxRate=" + taxRate + ", customer=" + customer
				+ ", employee=" + employee + ", shipper=" + shipper + super.toString() + "]";
	}

}
