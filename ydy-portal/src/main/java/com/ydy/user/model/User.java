package com.ydy.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ydy_user")
public class User{

	@Id
	@Column(name = "ID_", length = 50)
	private String id;

	@Column(name = "USER_MOBILE_", length = 11)
	private String userMobile;
	
	@Column(name = "PASSWORD_", length = 100)
	private String password;
	
	@Transient
	private String confrimPassword;
	@Transient
	private String rondomCode;

	@Column(name = "USER_NAME_", length = 100)
	private String userName;

	@Column(name = "USER_TYPE_", length = 1)
	private String userType;

	@Column(name = "RECOM_CODE_", length = 100)
	private String recomCode;

	@Column(name = "REFERRER_ID_", length = 50)
	private String referrerId;

	@Column(name = "REFERRER_NAME_", length = 100)
	private String referrerName;

	@Column(name = "USER_LEVEL_")
	private int userLevel;

	@Column(name = "ROOT_USER_ID_", length = 50)
	private String rootUserId;

	@Column(name = "HAS_CHILD_", length = 1)
	private String hasChild;

	@Column(name = "CREATE_TIME_")
	private Date createTime;
	
	@Column(name = "DELETE_FLAG_", length = 1)
	private String deleteFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRecomCode() {
		return recomCode;
	}

	public void setRecomCode(String recomCode) {
		this.recomCode = recomCode;
	}

	public String getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(String referrerId) {
		this.referrerId = referrerId;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getRootUserId() {
		return rootUserId;
	}

	public void setRootUserId(String rootUserId) {
		this.rootUserId = rootUserId;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRondomCode() {
		return rondomCode;
	}

	public void setRondomCode(String rondomCode) {
		this.rondomCode = rondomCode;
	}

	public String getConfrimPassword() {
		return confrimPassword;
	}

	public void setConfrimPassword(String confrimPassword) {
		this.confrimPassword = confrimPassword;
	}
	
	
}
