package com.ydy.utils;

public class Constants {
	public static final String YES = "Y";
	public static final String NO = "N";

	public static final String USER = "USER";
	public static final String WXUSER = "WXUSER";
	public static final String AUTH_TOKEN = "AUTH_TOKEN";
	
	public static final String VIS_BONUS = "同级奖";
	public static final String SERVER_BONUS = "服务奖";
	public static final String SALE_BONUS = "月度销售奖";
	
	
	public static final String CREATE_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	public static final String QUERY_TAG_URL = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
	public static final String BATCH_TAGGING_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
	
	public enum WxBtn {
		INDEX("index","御鼎园"),
		BOSS("boss", "我是老板"),BOSS_ENTRY("boss_entry", "进入系统"), 
		PARTNER("partner", "我是合伙人"), PARTNER_CENTER("partner_center", "个人中心");

		private String code;
		private String desc;

		WxBtn(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	} 
	
	public enum OrderStatus {
		ORDER_APPLY("0", "已申请"), ORDER_CONFIRM("1", "已确认"), ORDER_REJECT("2","已拒绝");

		private String code;
		private String desc;

		OrderStatus(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public enum UserType {
		USER_TYPE_BOSS("0", "老板"), USER_TYPE_PARTNER("0", "合伙人");

		private String code;
		private String desc;

		UserType(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}


	public enum UserStatus {
		USER_STATUS_REGISTER("0", "用户已注册，未审批"), USER_STATUS_VALID("1", "用户已审批");

		private String code;
		private String desc;

		UserStatus(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public enum SmsTemplate {
		register("SMS_103850023", "注册验证码");

		private String code;
		private String desc;

		SmsTemplate(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

}
