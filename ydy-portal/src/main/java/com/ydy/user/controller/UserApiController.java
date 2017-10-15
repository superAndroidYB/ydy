package com.ydy.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taobao.api.ApiException;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.ydy.dto.ResponseDto;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;
import com.ydy.utils.ISmsClient;

@RestController
public class UserApiController {

	@Resource(name = ISmsClient.BEAN_ID)
	private ISmsClient smsClient;
	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;

	@RequestMapping(path = "/doSendSms", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doSendSms(User user) {
		ResponseDto responseDto;
		boolean mobileExtis = userService.checkMobileExtis(user.getUserMobile());
		if(mobileExtis){
			responseDto = new ResponseDto(false, "失败！该手机号已经注册过了，请检查！");
			return ResponseEntity.ok(responseDto);
		}
		Map<String, String> map = new HashMap<String, String>();
		Random random = new Random();
		map.put("code", Integer.toString((int)random.nextInt(9999-1000+1)+1000));
		AlibabaAliqinFcSmsNumSendResponse res = null;
		try {
			res = smsClient.sendSms("1234", Constants.SmsTemplate.register, user.getUserMobile(), map);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String errorCode = res.getErrorCode();
		if ("0".equals(errorCode)) {
			responseDto = new ResponseDto(true, "成功！");
		} else {
			responseDto = new ResponseDto(false, "失败！" +res.getMsg());
		}
		return ResponseEntity.ok(responseDto);
	}

}
