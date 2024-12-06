package org.llin.demo.northwind.model;

import org.llin.demo.northwind._Values;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BaseObject implements Comparable<Object>, _Values {
			
	private Links links = new Links();
	
	@JsonIgnore
	private boolean ignore = false;

	@JsonIgnore
	private boolean moreThanOne = false;

	@JsonIgnore
	private int ellipsisLimit;

	@JsonIgnore
	private boolean abridged;

	@JsonIgnore
	private String abridgedNotes;

	@JsonIgnore
	int id;
	
	@JsonProperty("_links")
	@JsonDeserialize(using = LinksDeserializer.class)
	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return " BaseObject [ignore=" + ignore + ", moreThanOne=" + moreThanOne + ", ellipsisLimit="
				+ ellipsisLimit + ", abridged=" + abridged + ", abridgedNotes=" + abridgedNotes + ", id=" 
				+ id + ", " + links.toString() + "]";
	}

	@Override
	public int compareTo(Object o) {
		return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
	}
	
}
