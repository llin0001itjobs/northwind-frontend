package org.llin.demo.northwind.cache.type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.model.charts.LabelDoubleValueLong;
import org.llin.demo.northwind.model.charts.LabelIntValueDouble;
import org.llin.demo.northwind.model.charts.LabelValue;
import org.llin.demo.northwind.model.charts.LabelStringValueLong;
import org.llin.demo.northwind.model.charts.LabelStringValueLongValueDouble;
import org.springframework.stereotype.Component;

@Component
public class CustomSqlType<T extends LabelValue> implements _Classes_CustomObject, _Values {
	
	private Map<String, CustomSqlType<T>> mapOfType = new HashMap<>();
	
	private LabelValue[] labelValue = {};
	
	private String typeName;

	public CustomSqlType() {
		super();
	}

	public CustomSqlType(String typeName, LabelValue[] labelValue) {
		super();
		this.labelValue = labelValue;
		this.typeName = typeName;
	}

	@PostConstruct
	void init() {
		LabelStringValueLong[] lvl = {};
		LabelIntValueDouble[] livd = {};
		LabelStringValueLongValueDouble[] lvlvd = {};
		LabelDoubleValueLong[] ldvl = {};
		mapOfType.put(KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT, new CustomSqlType<>(LSVL,lvl));
		mapOfType.put(KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH, new CustomSqlType<>(LIVD,livd));
		mapOfType.put(KEY_PRODUCT_CATEGORY_RATIOS, new CustomSqlType<>(LSVLVD,lvlvd));
		mapOfType.put(KEY_PRODUCT_PRICE_RANGE_PER_LIST_PRICE, new CustomSqlType<>(LSVL,lvl));
		mapOfType.put(KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST, new CustomSqlType<>(LSVL,lvl));
		mapOfType.put(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_COST_RANGE, new CustomSqlType<>(LSVL,lvl));
		mapOfType.put(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_UNIT_COST, new CustomSqlType<>(LDVL,ldvl));	
	}
	
	public Map<String, CustomSqlType<T>> getMapOfType() {
		return mapOfType;
	}
	
	public LabelValue[] getLabelValue() {
		return labelValue;
	}

	public void setLabelValue(LabelValue[] labelValue) {
		this.labelValue = labelValue;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "CustomType [labelValue=" + Arrays.toString(labelValue) + ", typeName=" + typeName
				+ "]";
	}

}
