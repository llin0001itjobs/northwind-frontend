package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.TypeState;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/typeState")
public class TypeStateController<T extends TypeState> extends BaseController<T> {
	private static final String XSLT = "./xslt/typeState.xslt";
	private static final String JSON = "./xslt/sample/typeState.json";
	private TypeState[] typeStates = {};

	@SuppressWarnings("unchecked")
	public TypeStateController() {				
		_type = (Class<T[]>) typeStates.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	
	@GetMapping("/list")
	public ModelAndView getAllTypeStates() {
		return getAllObjects("typeState");
	}
		
	@Override
	void loadConfigList() {						
				
	}
}	
