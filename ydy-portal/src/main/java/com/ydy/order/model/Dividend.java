package com.ydy.order.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ydy.user.model.User;

@Entity
@Table(name = "ydy_dividend")
public class Dividend {

	@Id
	@Column(name = "ID_", length = 50)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID_")
	private Order order;

	@Column(name = "SUBJECT_", length = 50)
	private String subject;

	@Column(name = "ORDER_AMT_", length = 18, scale = 2)
	private BigDecimal orderAmt;
	
	@Column(name = "COEFFICIENT_", length = 18, scale = 4)
	private BigDecimal coefficient;
	
	@Column(name = "AMT_", length = 18, scale = 2)
	private BigDecimal amt;
	
	@Column(name = "MEMO_", length = 500)
	private String memo;
	
	@Column(name = "CREATE_TIME_",columnDefinition = "DATETIME default NOW() comment '创建时间'")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public BigDecimal getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}
	
}
