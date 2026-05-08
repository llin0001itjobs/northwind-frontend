package org.llin.demo.northwind.model.charts;

public class LabelStringValueLongValueDouble implements LabelValue {
	
	private String label;
	
	private Long valueLong;
	
	private Double valueDouble;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getValue() {
		return valueLong;
	}
	
	public Long getValueLong() {
		return valueLong;
	}
	
	public void setValueLong(long valueLong) {
		this.valueLong = valueLong;
	}

	public Double getValueDouble() {
		return valueDouble;
	}

	public void setValueDouble(Double valueDouble) {
		this.valueDouble = valueDouble;
	}

	@Override
	public String toString() {
		return "LabelStringValueLongValueDouble [label=" + label + ", valueLong=" + valueLong + ", valueDouble=" + valueDouble
				+ "]";
	}
 
}
