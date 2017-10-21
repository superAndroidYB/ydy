package com.ydy.utils;

public class Constants {
	public static final String YES = "Y";
	public static final String NO = "N";

	public static final String USER = "USER";
	
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
