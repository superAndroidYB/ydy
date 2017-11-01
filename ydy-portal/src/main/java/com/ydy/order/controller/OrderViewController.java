package com.ydy.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderViewController {
	
	@RequestMapping(value = "/stock", method = { RequestMethod.GET, RequestMethod.POST })
	public String stock(Model model) {
		return "stock";
	}
	
	
	@RequestMapping(value = "/confirm_stock", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmStock(Model model) {
		return "confirm_stock";
	}

}
