package com.ydy.utils;

import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service(ISmsClient.BEAN_ID)
public class SmsClient implements ISmsClient {

	@Value("${ali.url}")
	private String url;
	@Value("${ali.appKey}")
	private String appkey;
	@Value("${ali.appSecret}")
	private String secret;
	@Value("${ali.signName}")
	private String signName;

	@Override
	public AlibabaAliqinFcSmsNumSendResponse sendSms(String extend, Constants.SmsTemplate temp, String recNum,
			Map<String, String> params) throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(extend);
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		StringBuffer paramString = new StringBuffer("{");
		for (String key : params.keySet()) {
			paramString.append("\"" + key + "\":\"" + params.get(key) + "\",");
		}
		String paramS = paramString.substring(0, paramString.length() - 1) + "}";
		req.setSmsParamString(paramS);
		req.setRecNum(recNum);
		req.setSmsTemplateCode(temp.getCode());
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		return rsp;
	}

}
