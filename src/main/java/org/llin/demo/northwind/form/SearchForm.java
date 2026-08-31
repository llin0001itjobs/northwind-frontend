package org.llin.demo.northwind.form;

import jakarta.validation.constraints.NotBlank;

public class SearchForm {

	@NotBlank(message = "Search must not be blank")
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
}
