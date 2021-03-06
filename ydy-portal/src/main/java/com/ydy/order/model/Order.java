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
@Table(name = "ydy_order")
public class Order {

	@Id
	@Column(name = "ID_", length = 50)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private User user;

	@Column(name = "ADDRESS_ID_", length = 50)
	private String addressId;

	@Column(name = "ADDRESS_", length = 500)
	private String addressStr;

	@Column(name = "APPLY_NUM_", length = 18, scale = 2)
	private BigDecimal applyNum;

	@Column(name = "APPLY_UNIT_PRIC_", length = 18, scale = 2)
	private BigDecimal applyUnitPric;

	@Column(name = "APPLY_AMT_", length = 18, scale = 2)
	private BigDecimal applyAmt;

	@Column(name = "APPLY_MEMO_", length = 500)
	private String applyMemo;

	@Column(name = "AGREE_NUM_", length = 18, scale = 2)
	private BigDecimal agreeNum;

	@Column(name = "AGREE_UNIT_PRIC_", length = 18, scale = 2)
	private BigDecimal agreeUnitPric;

	@Column(name = "AGREE_AMT_", length = 18, scale = 2)
	private BigDecimal agreeAmt;

	@Column(name = "AGREE_MEMO_", length = 500)
	private String agreeMemo;

	@Column(name = "STATUS_", length = 1)
	private String status;

	@Column(name = "CREATE_TIME_")
	private Date createTime;

	@Column(name = "AGREE_TIME_")
	private Date agreeTime;
	
	@Column(name = "FIRST_FLAG_", length = 1)
	private String firstFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(BigDecimal applyNum) {
		this.applyNum = applyNum;
	}

	public BigDecimal getApplyUnitPric() {
		return applyUnitPric;
	}

	public void setApplyUnitPric(BigDecimal applyUnitPric) {
		this.applyUnitPric = applyUnitPric;
	}

	public BigDecimal getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}

	public String getApplyMemo() {
		return applyMemo;
	}

	public void setApplyMemo(String applyMemo) {
		this.applyMemo = applyMemo;
	}

	public BigDecimal getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(BigDecimal agreeNum) {
		this.agreeNum = agreeNum;
	}

	public BigDecimal getAgreeUnitPric() {
		return agreeUnitPric;
	}

	public void setAgreeUnitPric(BigDecimal agreeUnitPric) {
		this.agreeUnitPric = agreeUnitPric;
	}

	public BigDecimal getAgreeAmt() {
		return agreeAmt;
	}

	public void setAgreeAmt(BigDecimal agreeAmt) {
		this.agreeAmt = agreeAmt;
	}

	public String getAgreeMemo() {
		return agreeMemo;
	}

	public void setAgreeMemo(String agreeMemo) {
		this.agreeMemo = agreeMemo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Date agreeTime) {
		this.agreeTime = agreeTime;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(String firstFlag) {
		this.firstFlag = firstFlag;
	}



}
