package com.ydy.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ydy_address")
public class Address {
	
	@Id
	@Column(name = "ID_", length = 50)
	private String id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID_")
	private User user;
	
	@Column(name = "ADDRESS_", length = 4000)
	private String address;
	
	@Column(name = "IS_DEFAULT_", length = 1)
	private String isDefault;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	

}
