package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Authentication;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController<T extends Authentication> extends BaseController<T> {
	
	private static final String XSLT = "./xslt/authentication.xslt";
	private static final String JSON = "./xslt/sample/authentication.json";
	private Authentication[] authentications = {};
	
	@SuppressWarnings("unchecked")
	public AuthenticationController() {
		_type = (Class<T[]>) authentications.getClass();		
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));		
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	
	@GetMapping("/list")
	public ModelAndView getAllAuthentications() {
		return getAllObjects("authentication");
	}

	@Override
	void loadConfigList() {
		//do nothing
	}

}
