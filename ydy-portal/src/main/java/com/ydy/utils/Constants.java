package com.ydy.utils;

public class Constants {
	public static final String YES = "Y";
	public static final String NO = "N";
	
	public static final String USER_TYPE_BOSS = "1";//老板
	public static final String USER_TYPE_PARTNER = "2";//合伙人
	
	public static final String USER = "USER";

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
