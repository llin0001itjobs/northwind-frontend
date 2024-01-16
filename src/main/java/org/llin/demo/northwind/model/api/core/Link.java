package org.llin.demo.northwind.model;

public class Link {

	private String label;
	
	private String href;
	
	public Link() {
		// TODO Auto-generated constructor stub
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "\nLink [label=" + label + ", href=" + href + "]";
	}

	
	
}
