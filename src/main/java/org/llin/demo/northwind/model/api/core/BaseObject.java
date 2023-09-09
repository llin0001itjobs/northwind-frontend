package org.llin.demo.northwind.model.api.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BaseObject implements Comparable<Object> {
	
	Class<?> _type;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	String label;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private boolean ignore = false;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private boolean moreThanOne = false;
	
	private Link[] links;
	
	private List<BaseObject> children = new ArrayList<>();
		
	@JsonIgnoreProperties(ignoreUnknown = true)
	private int ellipsisLimit;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private boolean abridged;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String abridgedNotes;
				
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}
	
	public boolean isMoreThanOne() {
		return moreThanOne;
	}

	public void setMoreThanOne(boolean moreThanOne) {
		this.moreThanOne = moreThanOne;
	}

	public Link[] getLinks() {
		return links;
	}

	public void setLinks(Link[] links) {
		this.links = links;
	}

	public List<BaseObject> getChildren() {
		return children;
	}

	public void setChildren(List<BaseObject> children) {
		this.children = children;
	}
	
	public int getEllipsisLimit() {
		return ellipsisLimit;
	}

	public void setEllipsisLimit(int ellipsisLimit) {
		this.ellipsisLimit = ellipsisLimit;
	}
	
	public boolean isAbridged() {
		return abridged;
	}

	public void setAbridged(boolean abridged) {
		this.abridged = abridged;
	}

	public String getAbridgedNotes() {
		return abridgedNotes;
	}

	public void setAbridgedNotes(String abridgedNotes) {
		this.abridgedNotes = abridgedNotes;
	}

	@Override
	public String toString() {
		return "\nBaseObject [_type=" + _type + ", label=" + label + ", links=" + Arrays.toString(links) + ", children="
				+ children + "]";
	}

	@Override
	public int compareTo(Object o) {		
		return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
	}


}
