package org.llin.demo.northwind.menu;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuEntities<T extends MenuEntity> {
	
	private List<T> firstOrder = new ArrayList<>();
	
	private List<T> secondOrder = new ArrayList<>();
	
	private List<T> thirdOrder = new ArrayList<>();
	
	private List<T> fourthOrder = new ArrayList<>();
	
	@JsonProperty("type")
	private List<T> type = new ArrayList<>();
	
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

	public List<T> getThirdOrder() {
		return thirdOrder;
	}

	public void setThirdOrder(List<T> thirdOrder) {
		this.thirdOrder = thirdOrder;
	}

	public List<T> getFourthOrder() {
		return fourthOrder;
	}

	public void setFourthOrder(List<T> fourthOrder) {
		this.fourthOrder = fourthOrder;
	}
	
	public List<T> getType() {
		return type;
	}

	public void setType(List<T> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MenuEntities [firstOrder=" + firstOrder + ", secondOrder=" + secondOrder + ", thirdOrder="
				+ thirdOrder + ", fourthOrder=" + fourthOrder +", type=" + type + "]";
	}


	
}
