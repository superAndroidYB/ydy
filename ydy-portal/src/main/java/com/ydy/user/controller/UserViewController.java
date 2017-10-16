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

	@RequestMapping(path = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(path = "/partner", method = { RequestMethod.GET, RequestMethod.POST })
	public String partner(Model model) {
		return "partner";
	}

	@RequestMapping(path = "/sale_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String saleDetail(Model model) {
		return "sale_detail";
	}

	@RequestMapping(path = "/confirm", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirm(Model model) {
		return "confirm";
	}

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

	@RequestMapping(value = "/order", method = { RequestMethod.GET, RequestMethod.POST })
	public String order(Model model) {
		return "order";
	}

	@RequestMapping(value = "/dividend", method = { RequestMethod.GET, RequestMethod.POST })
	public String dividend(Model model) {
		return "dividend";
	}

	@RequestMapping(value = "/dividend_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String dividendDetail(Model model) {
		return "dividend_detail";
	}
	
	@RequestMapping(value = "/wait", method = { RequestMethod.GET, RequestMethod.POST })
	public String wait(Model model){
		return "wait";
	}
}
