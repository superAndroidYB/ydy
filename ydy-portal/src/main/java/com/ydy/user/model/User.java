package com.ydy.user.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ydy.order.model.Order;

@Entity
@Table(name = "ydy_user")
public class User {

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
	@Transient
	private String address;

	@Column(name = "USER_NAME_", length = 100)
	private String userName;

	@Column(name = "USER_TYPE_", length = 1)
	private String userType;

	@Column(name = "RECOM_CODE_", length = 100)
	private String recomCode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRER_ID_")
	private User referrerUser;

	@Column(name = "USER_LEVEL_")
	private int userLevel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOT_USER_ID_")
	private User rootUser;

	//@OneToMany(mappedBy = "user")
	//private Set<Address> addresses;
	
	//@OneToMany(mappedBy = "user")
	//private Set<Order> orders;

	@Column(name = "CREATE_TIME_")
	private Date createTime;

	@Column(name = "STATUS_", length = 1)
	private String status;

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

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getReferrerUser() {
		return referrerUser;
	}

	public void setReferrerUser(User referrerUser) {
		this.referrerUser = referrerUser;
	}

	public User getRootUser() {
		return rootUser;
	}

	public void setRootUser(User rootUser) {
		this.rootUser = rootUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public Set<Address> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(Set<Address> addresses) {
//		this.addresses = addresses;
//	}
//
//	public Set<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
	

}
