package com.ydy.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;

@Controller
public class UserViewController {

	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;

	@RequestMapping(path = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {
		model.addAttribute("indexDto", userService.getIndexData());
		return "index";
	}

	@RequestMapping(path = "/partner", method = { RequestMethod.GET, RequestMethod.POST })
	public String partner(Model model) {
		return "partner";
	}

	@RequestMapping(path = "/boss_partner", method = { RequestMethod.GET, RequestMethod.POST })
	public String bossPartner(Model model) {
		model.addAttribute("unUserList", userService.getAllUndeterminedUser());
		model.addAttribute("allUserList", userService.getAllUndeterminedUser());
		return "boss_partner";
	}

	@RequestMapping(path = "/sale_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String saleDetail(Model model) {
		return "sale_detail";
	}

	@RequestMapping(path = "/confirm", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirm(String id, Model model) {
		model.addAttribute("user", userService.findUserById(id));
		return "confirm";
	}

	@RequestMapping(path = "/register_info", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerInfo(String id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "register_info";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/user_center", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCenter(Model model, HttpSession session) {
		//User user = (User) session.getAttribute(Constants.USER);
		User user = new User();
		user.setRecomCode("34504503");
		user.setUserName("Jeck");
		User referrerUser = new User();
		referrerUser.setRecomCode("34504503");
		referrerUser.setUserName("Rose");
		user.setReferrerUser(referrerUser);
		model.addAttribute("user", user);
		return "user_center";
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
	public String wait(Model model) {
		model.addAttribute("tip", "注册成功！");
		return "wait";
	}


}
