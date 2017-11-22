package com.ydy.order.model;

import java.math.BigDecimal;
import java.util.List;

public class DivdendDto {
	
	private String month;
	private BigDecimal amt;
	private String subject;
	private String desc;
	private List<Dividend> dividends;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Dividend> getDividends() {
		return dividends;
	}
	public void setDividends(List<Dividend> dividends) {
		this.dividends = dividends;
	}


	
	
	
}

