package org.llin.demo.northwind.model;

public class Link {

	private String id;
	
	private String label;
	
	private String href;
	
	public Link() {}

	public Link(String label) {
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "Link [id=" + id + ", label=" + label + ", href=" + href + "]";
	}
	
}
