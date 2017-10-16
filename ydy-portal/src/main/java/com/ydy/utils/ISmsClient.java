package com.ydy.utils;

import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public interface ISmsClient {
	
	public static final String BEAN_ID = "util.smsclient";
	
	public AlibabaAliqinFcSmsNumSendResponse sendSms(String extend, Constants.SmsTemplate temp, String recNum,
			Map<String, String> params) throws ApiException;

}
