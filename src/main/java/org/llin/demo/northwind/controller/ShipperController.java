package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Shipper;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shipper")
public class ShipperController<T extends Shipper> extends BaseController<T> {

	private static final String XSLT = "./xslt/shipper.xslt";
	private static final String JSON = "./xslt/sample/shipper.json";
	private Shipper[] shippers = {};
	
	@SuppressWarnings("unchecked")
	public ShipperController() {
		_type = (Class<T[]>) shippers.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));	
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
		
	}

	@GetMapping("/list")
	public ModelAndView getAllShippers() {
		return getAllObjects("shipper");
	}

	@Override
	void loadConfigList() {
		_configList.add("Company");
	}
}
