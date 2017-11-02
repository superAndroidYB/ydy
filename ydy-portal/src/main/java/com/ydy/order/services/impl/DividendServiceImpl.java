package com.ydy.order.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.order.model.Dividend;
import com.ydy.order.model.Order;
import com.ydy.order.services.IDividendService;
import com.ydy.user.dao.DividendJpaDao;
import com.ydy.user.dao.OrderJpaDao;
import com.ydy.utils.Constants;

@Service(IDividendService.BEAN_ID)
public class DividendServiceImpl implements IDividendService {

	@Autowired
	OrderJpaDao orderDao;
	@Autowired
	DividendJpaDao dividendDao;

	@Override
	public void calcMonthSaleAmt() {
		List<Order> orderList = orderDao.findByStatusAndCreateTimeLike(Constants.OrderStatus.ORDER_CONFIRM.getCode(),
				DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "%");
		List<Dividend> dividendList = new ArrayList<>();
		for (Order order : orderList) {
			if (Constants.YES.equals(order.getFirstFlag())) {
				Dividend div = new Dividend();
				div.setId(UUID.randomUUID().toString());
				div.setUser(order.getUser());
				div.setOrder(order);
				div.setSubject(Constants.VIS_BONUS);
				div.setOrderAmt(order.getAgreeAmt());
				BigDecimal coefficient = new BigDecimal("0.10");
				div.setCoefficient(coefficient);
				div.setAmt(coefficient.multiply(order.getAgreeAmt()));
				div.setMemo("首批进货10%的同级奖");
				dividendList.add(div);
			} else {
				Dividend div = new Dividend();
				div.setId(UUID.randomUUID().toString());
				div.setUser(order.getUser());
				div.setOrder(order);
				div.setSubject(Constants.SERVER_BONUS);
				div.setOrderAmt(order.getAgreeAmt());
				BigDecimal coefficient = new BigDecimal("0.05");
				div.setCoefficient(coefficient);
				div.setAmt(coefficient.multiply(order.getAgreeAmt()));
				div.setMemo("永续经营5%的服务奖");
				dividendList.add(div);
			}
		}
		
		dividendDao.save(dividendList);
	}

}
