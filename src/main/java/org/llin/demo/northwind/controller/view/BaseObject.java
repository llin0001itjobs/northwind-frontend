package org.llin.demo.northwind.controller.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BaseObject implements Comparable<Object> {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String label;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private boolean ignore = false;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private boolean moreThanOne = false;
	
	private Href self;
	
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

	public Href getSelf() {
		return self;
	}

	public void setSelf(Href self) {
		this.self = self;
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
		return "\nBaseObject [self=" + self + ", abridged=" + abridged + ", abridgedNotes=" + abridgedNotes + "]";
	}

	@Override
	public int compareTo(Object o) {		
		return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
	}
			
}
