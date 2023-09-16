package org.llin.demo.northwind.model.api.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TypeState extends BaseObject {
	
	@JsonIgnore
	private String code;

	private String description;

	public TypeState() {
		super();
	}

	public TypeState(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "\nTypeState [code=" + code + ", description=" + description + super.toString() + "]";
	}

}
