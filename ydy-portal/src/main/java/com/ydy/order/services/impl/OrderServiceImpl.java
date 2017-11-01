package com.ydy.order.services.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.dto.ResponseDto;
import com.ydy.order.model.Order;
import com.ydy.order.services.IOrderService;
import com.ydy.user.dao.OrderJpaDao;
import com.ydy.user.model.User;
import com.ydy.utils.Constants;

@Service(IOrderService.BEAN_ID)
public class OrderServiceImpl implements IOrderService {
	
	
	@Autowired
	public OrderJpaDao orderDao;
	

	@Override
	public ResponseDto doStock(Order order, User user) {
		order.setId(UUID.randomUUID().toString());
		order.setUser(user);
		order.setStatus(Constants.OrderStatus.ORDER_APPLY.getCode());
		Order save = orderDao.save(order);
		return new ResponseDto(true, "成功", save);
	}


	@Override
	public ResponseDto doConfirmStock(Order order) {
		if(StringUtils.isNotBlank(order.getId())){
			Order orderDB = orderDao.getOne(order.getId());
			orderDB.setAgreelyNum(order.getAgreelyNum());
			orderDB.setAgreeMemo(order.getAgreeMemo());
			orderDB.setAgreeTime(new Date());
			orderDB.setAgreeUnitPric(order.getAgreeUnitPric());
			orderDB.setAgreeAmt(order.getAgreelyNum().multiply(order.getAgreeUnitPric()));
			order.setStatus(Constants.OrderStatus.ORDER_CONFIRM.getCode());
			Order save = orderDao.save(orderDB);
			return new ResponseDto(true, "成功", save);
		}
		return new ResponseDto(false, "参数缺失！");
	}


	@Override
	public ResponseDto doRejectStock(Order order) {
		if(StringUtils.isNotBlank(order.getId())){
			Order orderDB = orderDao.getOne(order.getId());
			orderDB.setAgreelyNum(order.getAgreelyNum());
			orderDB.setAgreeMemo(order.getAgreeMemo());
			orderDB.setAgreeTime(new Date());
			orderDB.setAgreeUnitPric(order.getAgreeUnitPric());
			//orderDB.setAgreeAmt(order.getAgreelyNum().multiply(order.getAgreeUnitPric()));
			order.setStatus(Constants.OrderStatus.ORDER_REJECT.getCode());
			Order save = orderDao.save(orderDB);
			return new ResponseDto(true, "成功", save);
		}
		return new ResponseDto(false, "参数缺失！");
	}

}
