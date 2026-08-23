package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.UserDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	@Autowired
	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/list")
	public ModelAndView getAllUsers() {
		return createDefaultModelAndView();
	}

	@GetMapping("/findByUsername/{name}")
	public ModelAndView findByUsername(@PathVariable String name) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<UserDto> opt = service.findByUsername(name);

		if (opt.isPresent()) {
			mav.addObject(USER, opt.get());
			mav.setViewName("users/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/findByEmail/{email}")
	public ModelAndView findByEmail(@PathVariable String email) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<UserDto> opt = service.findByEmail(email);

		if (opt.isPresent()) {
			mav.addObject(USER, opt.get());
			mav.setViewName("users/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/findByVerificationToken/{token}")
	public ModelAndView findByVerificationToken(@PathVariable String token) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<UserDto> opt = service.findByVerificationToken(token);

		if (opt.isPresent()) {
			mav.addObject(USER, opt.get());
			mav.setViewName("users/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(USERS, service.findAll());
		mv.addObject(TITLE, TITLE_USER);
		mv.setViewName("entities/role");
		return mv;
	}
}
