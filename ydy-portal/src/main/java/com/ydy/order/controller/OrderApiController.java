package com.ydy.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ydy.dto.ResponseDto;
import com.ydy.order.model.Order;
import com.ydy.user.services.IAddressService;

@RestController
public class OrderApiController {
	
	@Resource(name = IAddressService.BEAN_ID)
	private IAddressService addressService;

	@RequestMapping(path = "/doStock", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doStock(Order order, HttpSession session){
		return null;
	}

}
