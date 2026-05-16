package org.llin.demo.northwind.model.analytics;

public class LabelIntValueLong implements LabelValue {

	private Integer label;

	private Long value;

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
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
		return "LabelIntValueLong [label=" + label + ", value=" + value + "]";
	}

}
