package com.ydy.order.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.order.services.IOrderService;
import com.ydy.user.model.User;
import com.ydy.utils.Constants;

@Controller
public class OrderViewController {
	
	@Autowired
	IOrderService orderService;
	
	@RequestMapping(value = "/stock", method = { RequestMethod.GET, RequestMethod.POST })
	public String stock(Model model) {
		return "stock";
	}
	
	
	@RequestMapping(value = "/confirm_stock", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmStock(Model model) {
		return "confirm_stock";
	}


	@RequestMapping(value = "/order", method = { RequestMethod.GET, RequestMethod.POST })
	public String order(Model model,HttpSession session) {
		User user = (User)session.getAttribute(Constants.USER);
		model.addAttribute("applyList",orderService.getOrderListByStatus(Constants.OrderStatus.ORDER_APPLY.getCode(), user));
		model.addAttribute("confirmList",orderService.getOrderListByStatus(Constants.OrderStatus.ORDER_CONFIRM.getCode(), user));
		model.addAttribute("rejectList",orderService.getOrderListByStatus(Constants.OrderStatus.ORDER_REJECT.getCode(), user));
		return "order";
	}
	
	@RequestMapping(value = "/stockWait", method = { RequestMethod.GET, RequestMethod.POST })
	public String stockWait(Model model) {
		model.addAttribute("tip", "申请进货成功！");
		return "wait";
	}
}
