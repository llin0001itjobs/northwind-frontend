package org.llin.demo.northwind.model.analytics;

public class LabelIntValueDouble implements LabelValue {
	
	private Integer label;
	
	private Double value;

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelIntValueDouble [label=" + label + ", value=" + value + "]";
	}
	
}
