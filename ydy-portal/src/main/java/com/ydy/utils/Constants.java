package com.ydy.utils;

public class Constants {
	public static final String YES = "Y";
	public static final String NO = "N";

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
