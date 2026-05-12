package org.llin.demo.northwind.controller;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reports")
public class ReportsController<T extends EntityObject> implements _Classes_EntityObject, _Classes_CustomObject {

	private ModelAndView mvReports = new ModelAndView();

	@PostConstruct
	private void init() {

	}

	@GetMapping("/main")
	public ModelAndView main() {
		return new ModelAndView("reports/main");
	}

	@GetMapping("/histograms")
	public ModelAndView histograms() {
		mvReports.setViewName("reports/histograms");
		return mvReports;
	}

	@GetMapping("/pie-charts")
	public ModelAndView pieCharts() {
		mvReports.setViewName("reports/pie-charts");
		return mvReports;
	}

}
