package com.ydy.order.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.dto.ResponseDto;
import com.ydy.order.model.Order;
import com.ydy.order.services.IOrderService;
import com.ydy.user.dao.OrderJpaDao;
import com.ydy.user.model.User;

@Service(IOrderService.BEAN_ID)
public class OrderServiceImpl implements IOrderService {
	
	
	//@Autowired
	public OrderJpaDao orderDao;
	

	@Override
	public ResponseDto doStock(Order order, User user) {
		order.setId(UUID.randomUUID().toString());
		//order.setUser(user);
		//Order save = orderDao.save(order);
		//return new ResponseDto(true, "成功", save);
		return null;
	}

}
