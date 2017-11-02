package com.ydy.order.services;

import java.util.List;

import com.ydy.dto.ResponseDto;
import com.ydy.order.model.Order;
import com.ydy.user.model.User;

public interface IOrderService {
	
	public static final String BEAN_ID = "order.OrderService";
	
	public ResponseDto doStock(Order order, User user);
	
	public ResponseDto doConfirmStock(Order order);
	
	public ResponseDto doRejectStock(Order order);
	
	public List<Order> getAllOrder();
	
	public List<Order> getOrderListByStatus(String status,User user);

}
