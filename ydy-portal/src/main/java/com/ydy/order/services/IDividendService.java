package com.ydy.order.services;

import java.util.List;

import com.ydy.order.model.DivdendDto;
import com.ydy.order.model.Dividend;
import com.ydy.user.model.User;

public interface IDividendService {
	public static final String BEAN_ID = "dividendService";
	
	public void calcMonthSaleAmt(String month);
	
	public void calcMonthSaleRelaAmt(String month);
	
	public List<DivdendDto> getDividendInfo(String userId, String subject);
	
	public List<Dividend> getDividendList(String userId, String subject);
}
