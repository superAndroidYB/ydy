package com.ydy.order.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ydy.order.model.DivdendDto;
import com.ydy.order.model.Dividend;
import com.ydy.order.model.Order;
import com.ydy.order.services.IDividendService;
import com.ydy.user.dao.DividendJpaDao;
import com.ydy.user.dao.OrderJpaDao;
import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;

@Service(IDividendService.BEAN_ID)
public class DividendServiceImpl implements IDividendService {

	@Autowired
	OrderJpaDao orderDao;
	@Autowired
	DividendJpaDao dividendDao;
	@Autowired
	UserJpaDao userDao;

	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;

	@Override
	public void calcMonthSaleAmt(String month) {
		List<Order> orderList = orderDao.findByStatusAndCreateTimeLike(Constants.OrderStatus.ORDER_CONFIRM.getCode(),
				month + "%");
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
				
				if(order.getUser().getReferrerUser() != null){
					Dividend divRef = new Dividend();
					divRef.setId(UUID.randomUUID().toString());
					divRef.setUser(order.getUser());
					divRef.setOrder(order);
					divRef.setSubject(Constants.VIS_BONUS);
					divRef.setOrderAmt(order.getAgreeAmt());
					BigDecimal coefficientRef = new BigDecimal("0.10");
					divRef.setCoefficient(coefficientRef);
					divRef.setAmt(coefficientRef.multiply(order.getAgreeAmt()));
					divRef.setMemo("首批进货10%的同级奖");
					dividendList.add(divRef);
				}
			} else {
				if(order.getUser().getReferrerUser() != null){
					Dividend div = new Dividend();
					div.setId(UUID.randomUUID().toString());
					div.setUser(order.getUser().getReferrerUser());
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
		}

		dividendDao.save(dividendList);
	}

	@Override
	public void calcMonthSaleRelaAmt(String month) {
		List<User> allUser = userDao.findAll();
		List<Dividend> dividendList = new ArrayList<>();
		// 计算所有下级的总金额获得系数
		for (User user : allUser) {
			String memo = "";
			BigDecimal totalAmt = clacAllUserAmt(user, BigDecimal.ZERO, month);
			BigDecimal coefficient = BigDecimal.ZERO;
			if (totalAmt.compareTo(new BigDecimal(10000000)) > 0) {
				coefficient = new BigDecimal(0.10);
				memo = "月度销售金额为" + totalAmt + ",超过了1000万，提成比例为10%";
			} else if (totalAmt.compareTo(new BigDecimal(5000000)) > 0) {
				coefficient = new BigDecimal(0.09);
				memo = "月度销售金额为" + totalAmt + ",超过了500万，提成比例为9%";
			} else if (totalAmt.compareTo(new BigDecimal(1000000)) > 0) {
				coefficient = new BigDecimal(0.08);
				memo = "月度销售金额为" + totalAmt + ",超过了100万，提成比例为8%";
			} else if (totalAmt.compareTo(new BigDecimal(800000)) > 0) {
				coefficient = new BigDecimal(0.07);
				memo = "月度销售金额为" + totalAmt + ",超过了80万，提成比例为7%";
			} else if (totalAmt.compareTo(new BigDecimal(500000)) > 0) {
				coefficient = new BigDecimal(0.06);
				memo = "月度销售金额为" + totalAmt + ",超过了50万，提成比例为6%";
			} else if (totalAmt.compareTo(new BigDecimal(300000)) > 0) {
				coefficient = new BigDecimal(0.05);
				memo = "月度销售金额为" + totalAmt + ",超过了30万，提成比例为5%";
			} else if (totalAmt.compareTo(new BigDecimal(200000)) > 0) {
				coefficient = new BigDecimal(0.04);
				memo = "月度销售金额为" + totalAmt + ",超过了20万，提成比例为4%";
			} else if (totalAmt.compareTo(new BigDecimal(100000)) > 0) {
				coefficient = new BigDecimal(0.03);
				memo = "月度销售金额为" + totalAmt + ",超过了10万，提成比例为3%";
			} else {
				continue;
			}

			BigDecimal subtrahend = BigDecimal.ZERO;
			List<Order> orderList = orderDao.findByUserIdAndStatusAndCreateTimeLike(user.getId(),
					Constants.OrderStatus.ORDER_CONFIRM.getCode(), month);
			for (Order order : orderList) {
				subtrahend = subtrahend.add(order.getAgreeAmt() == null ? BigDecimal.ZERO : order.getAgreeAmt());
			}

			totalAmt = totalAmt.subtract(subtrahend);

			memo += "去除本人的订单金额" + subtrahend + "后，使用" + totalAmt + "进行提成计算";

			Dividend div = new Dividend();
			div.setId(UUID.randomUUID().toString());
			div.setUser(user);
			div.setOrder(null);
			div.setSubject(Constants.SALE_BONUS);
			div.setOrderAmt(totalAmt);
			div.setCoefficient(coefficient);
			div.setAmt(coefficient.multiply(totalAmt));
			div.setMemo(memo);
			dividendList.add(div);
		}

	}

	public BigDecimal clacAllUserAmt(User user, BigDecimal amt, String month) {
		List<Order> orderList = orderDao.findByUserIdAndStatusAndCreateTimeLike(user.getId(),
				Constants.OrderStatus.ORDER_CONFIRM.getCode(), month);
		for (Order order : orderList) {
			amt = amt.add(order.getAgreeAmt() == null ? BigDecimal.ZERO : order.getAgreeAmt());
		}
		List<User> nextUserList = userService.getNextUserList(user);
		if (CollectionUtils.isEmpty(nextUserList)) {
			return amt;
		} else {
			for (User user2 : nextUserList) {
				amt = amt.add(clacAllUserAmt(user2, amt, month));
			}
			return amt;
		}

	}

	@Override
	public List<DivdendDto> getDividendInfo(String userId, String subject) {
		List<DivdendDto> dtoList = new ArrayList<>();
		List<String> months = dividendDao.getMonths();
		if (CollectionUtils.isEmpty(months)) {
			return null;
		}

		for (String string : months) {
			DivdendDto dto = new DivdendDto();
			dto.setMonth(string);

			BigDecimal amt = dividendDao.getSumAmtByUser(userId, subject, string);
			dto.setAmt(amt == null ? BigDecimal.ZERO : amt);
			dto.setSubject(subject);
			
			List<Dividend> dividends = getDividendList(userId, subject);
			dto.setDividends(dividends);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public List<Dividend> getDividendList(String userId, String subject) {
		return dividendDao.findByUserIdAndSubjectOrderByCreateTimeDesc(userId, subject);
	}

}
