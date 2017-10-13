package com.ydy.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.user.services.IUserService;

@Controller
public class UserViewController {

	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;

	/**
	 * 首页
	 */
	@RequestMapping(path = "/register_info", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerInfo() {
		return "register_info";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		model.addAttribute("hello", "Hello World!");
		return "login";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public String register(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/stock", method = { RequestMethod.GET, RequestMethod.POST })
	public String stock(Model model) {
		return "stock";
	}
	
	@RequestMapping(value = "/user_center", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCenter(Model model) {
		return "user_center";
	}

}
