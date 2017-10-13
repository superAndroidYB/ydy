package com.ydy.constants;

public class HttpConstants {
	
	public static String TEST_APP_ID = "wx4eb79f286e45ba91";
	public static String TEST_APP_SECRET = "84a4212c372d510aa34c2b7cf877347b";
	public static String APP_ID = "wxe53bf2477437a71f";
	public static String APP_SECRET = "6697ef310fa5ad3799dfe97925fe43d8";
	public static String LOCAL_IP = "121.34.28.156";// 公司电脑
	
	public static final String BASE_URL = "https://api.weixin.qq.com";
	public static final String GET_TOKEN = BASE_URL+"/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	public static final String GET_USER_INFO = BASE_URL + "/cgi-bin/user/info?access_token=%s&openid=%s";
	

}
