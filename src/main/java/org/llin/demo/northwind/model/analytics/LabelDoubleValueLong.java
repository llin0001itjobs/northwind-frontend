package org.llin.demo.northwind.model.analytics;

public class LabelDoubleValueLong implements LabelValue {
	
	private Double label;
	
	private Long value;

	public Double getLabel() {
		return label;
	}

	public void setLabel(Double label) {
		this.label = label;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelDoubleValueLong [label=" + label + ", value=" + value + "]";
	}
	
}
