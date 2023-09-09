package org.llin.demo.northwind.model.api.core;

import java.util.List;

public class Product extends BaseObject {

	private String productCode;

	private String productName;

	private String description;

	private double standardCost;

	private double listPrice;

	private Integer reorderLevel;

	private Integer targetLevel;

	private String quantityPerUnit;

	private boolean discontinued;

	private Integer minimumReorderQuantity;

	private String category;

	private int resourceId;
	
	private Product[] self = {};
	
	public Product() {
		super();
		_type = self.getClass();
		label = "Product"; 
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(Integer targetLevel) {
		this.targetLevel = targetLevel;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Integer getMinimumReorderQuantity() {
		return minimumReorderQuantity;
	}

	public void setMinimumReorderQuantity(Integer minimumReorderQuantity) {
		this.minimumReorderQuantity = minimumReorderQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	@Override
	public String toString() {
		return "\nProducts [productCode=" + productCode + ", productName="
				+ productName + ", description=" + description + ", standardCost=" + standardCost + ", listPrice="
				+ listPrice + ", reorderLevel=" + reorderLevel + ", targetLevel=" + targetLevel + ", quantityPerUnit="
				+ quantityPerUnit + ", discontinued=" + discontinued + ", minimumReorderQuantity="
				+ minimumReorderQuantity + ", category=" + category + ", resourceId=" + resourceId  
				+ super.toString() + "]";
	}



}
