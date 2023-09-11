package org.llin.demo.northwind.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.llin.demo.northwind.model.api.core.Role;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController<T extends Role> extends BaseController<T> {
	private static final String XSLT = "./xslt/role.xslt";
	private static final String JSON = "./xslt/sample/role.json";
	private Role[] roles = {};

	@SuppressWarnings("unchecked")
	public RoleController() {				
		_type = (Class<T[]>) roles.getClass();
		_xsltTransformer = new XsltTransformer(BaseController.class.getResourceAsStream(XSLT));
		_jsonReader = new BufferedReader(new InputStreamReader(BaseController.class.getResourceAsStream(JSON)));
	}
	@GetMapping("/list")
	public ModelAndView getAllRoles() {
		ModelAndView mv = new ModelAndView(ENTITIES_PAGE);
		mv.addObject(ENTITIES, getAllObjects("role"));
		mv.addObject(TITLE, "Role");
		return mv;
	}
		
	@Override
	void loadConfigList() {						
				
	}
}	
