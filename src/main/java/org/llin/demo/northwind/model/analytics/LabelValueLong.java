package org.llin.demo.northwind.model.analytics;

public class LabelValueLong implements LabelValue {
	
	private String label;
	
	private Long value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
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
		return "LabelStringValueLong [label=" + label + ", value=" + value + "]";
	}
	
}
