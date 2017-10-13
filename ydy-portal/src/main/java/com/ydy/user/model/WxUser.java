package com.ydy.user.model;

import javax.persistence.Column;

public class WxUser {

	@Column(name = "SUBSCRIBE_")
	private int subscribe;
	@Column(name = "OPEN_ID_", length = 50)
	private String openid;
	@Column(name = "NICK_NAME_", length = 50)
	private String nickname;
	@Column(name = "SEX_")
	private int sex;
	@Column(name = "LANGUAGE_", length = 10)
	private String language;
	@Column(name = "CITY_", length = 50)
	private String city;
	@Column(name = "PROVINCE_", length = 50)
	private String province;
	@Column(name = "COUNTRY_", length = 50)
	private String country;
	@Column(name = "HEADIMG_URL_", length = 500)
	private String headimgurl;
	@Column(name = "SUBSCRIBE_TIME_", length = 50)
	private String subscribe_time;
	@Column(name = "REMARK_", length = 500)
	private String remark;
	@Column(name = "GROUP_ID_")
	private int groupid;

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

}
