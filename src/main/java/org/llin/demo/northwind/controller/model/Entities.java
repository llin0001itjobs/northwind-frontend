package org.llin.demo.northwind.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entities<T extends Entity> {
	
	private List<T> base = new ArrayList<>();
		
	@JsonProperty("first_order")
	private List<T> firstOrder = new ArrayList<>();
	
	@JsonProperty("second_order")
	private List<T> secondOrder = new ArrayList<>();
	
	public List<T> getBase() {
		return base;
	}
	
	public void setBase(List<T> base) {
		this.base = base;
	}
	
	public List<T> getFirstOrder() {
		return firstOrder;
	}
	
	public void setFirstOrder(List<T> firstOrder) {
		this.firstOrder = firstOrder;
	}
	
	public List<T> getSecondOrder() {
		return secondOrder;
	}
	
	public void setSecondOrder(List<T> secondOrder) {
		this.secondOrder = secondOrder;
	}

	@Override
	public String toString() {
		return "Entities [base=" + base + ", firstOrder=" + firstOrder + ", secondOrder=" + secondOrder + "]";
	}
		
}
