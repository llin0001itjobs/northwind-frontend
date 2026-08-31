package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.form.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

	@RequestMapping("/search")
	public String search(@ModelAttribute("searchForm") SearchForm form) {
		return "page-search";
	}
}
