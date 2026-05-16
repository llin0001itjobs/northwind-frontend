package org.llin.demo.northwind.model.analytics;

public class LabelValueDouble implements LabelValue {
	
	private String label;
	
	private Double value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
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
		return "LabelStringValueDouble [label=" + label + ", value=" + value + "]";
	}
	
}
