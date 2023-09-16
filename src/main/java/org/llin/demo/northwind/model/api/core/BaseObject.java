package org.llin.demo.northwind.model.api.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseObject implements Comparable<Object> {
	@JsonIgnore
	Class<?> _type;
		
	@JsonIgnore
	String[] excludedFields = {"excludedFields","self"};
	
	@JsonIgnore
	List<String> values = new ArrayList<>();
	
	@JsonIgnore
	private boolean ignore = false;
	
	@JsonIgnore
	private boolean moreThanOne = false;
	
	private Link[] links;
	
	private List<BaseObject> children = new ArrayList<>();
		
	@JsonIgnore
	private int ellipsisLimit;
	
	@JsonIgnore
	private boolean abridged;
	
	@JsonIgnore
	private String abridgedNotes;
				
	@JsonIgnore
	private int id;
	
	public BaseObject() {
		setFieldValues();
	}
	
	void setFieldValues() {
		values.add("id");
		for (Field f : this.getClass().getDeclaredFields()) {
			if (Arrays.asList(excludedFields).contains(f.getName())) {
				continue;
			} 
			values.add(f.getName());
		}
		
		System.out.println(this.getClass().getSimpleName() + ": "+ values);
	}
	
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseObject [ignore=" + ignore + ", moreThanOne=" + moreThanOne + ", links=" + Arrays.toString(links)
				+ ", children=" + children + ", ellipsisLimit=" + ellipsisLimit + ", abridged=" + abridged
				+ ", abridgedNotes=" + abridgedNotes + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Object o) {		
		return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
	}


}
