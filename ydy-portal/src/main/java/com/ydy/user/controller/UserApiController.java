package com.ydy.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taobao.api.ApiException;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.ydy.dto.ResponseDto;
import com.ydy.user.model.AddressDto;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;
import com.ydy.utils.ISmsClient;
import com.ydy.utils.MDFiveUtils;

@RestController
public class UserApiController {

	@Resource(name = ISmsClient.BEAN_ID)
	private ISmsClient smsClient;
	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;

	@RequestMapping(path = "/doSendSms", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doSendSms(User user, HttpSession session) {
		ResponseDto responseDto;
		boolean mobileExtis = userService.checkMobileExtis(user.getUserMobile());
		if(mobileExtis){
			responseDto = new ResponseDto(false, "这个手机号已经注册过啦！");
			return ResponseEntity.ok(responseDto);
		}
		Map<String, String> map = new HashMap<String, String>();
		Random random = new Random();
		String code = Integer.toString((int)random.nextInt(9999-1000+1)+1000);
		session.setAttribute("randomCode", code);
		map.put("code", code);
		System.out.println("code:"+code);
		AlibabaAliqinFcSmsNumSendResponse res = null;
		try {
			res = smsClient.sendSms("1234", Constants.SmsTemplate.register, user.getUserMobile(), map);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String errorCode = res.getErrorCode();
		if (StringUtils.isEmpty(errorCode)) {
			responseDto = new ResponseDto(true, "发送成功！");
		} else {
			responseDto = new ResponseDto(false, "失败！" +res.getMsg());
		}
		return ResponseEntity.ok(responseDto);
	}
	
	@RequestMapping(path = "/doRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doRegister(Model model, User user, HttpSession session) {
		ResponseDto responseDto = null;
		boolean mobileExtis = userService.checkMobileExtis(user.getUserMobile());
		if(mobileExtis){
			responseDto = new ResponseDto(false, "这个手机号已经注册过啦！");
			return ResponseEntity.ok(responseDto);
		}
		
		String code = (String) session.getAttribute("randomCode");
		System.out.println("code:"+code);
		if(StringUtils.equals(user.getRondomCode(), code)){
			User userSession = (User) session.getAttribute(Constants.USER);
			if(userSession != null){
				User userDB = userService.findUserById(userSession.getId());
				userDB.setUserMobile(user.getUserMobile());
				userDB.setPassword(MDFiveUtils.encrypt(user.getPassword()));
				userDB = userService.saveUser(userDB);
				responseDto = new ResponseDto(true, "注册成功！");
				responseDto.setData(userDB);
				model.addAttribute(Constants.USER, userDB);
			}else{
				user.setUserType(Constants.UserType.USER_TYPE_PARTNER.getCode());
				responseDto = new ResponseDto(true, "注册成功！");
				user.setPassword(MDFiveUtils.encrypt(user.getPassword()));
				user.setUserType(Constants.UserType.USER_TYPE_PARTNER.getCode());
				user.setStatus(Constants.UserStatus.USER_STATUS_REGISTER.getCode());
				User userDB = userService.doUserRegister(user);
				responseDto.setData(userDB);
				model.addAttribute(Constants.USER, userDB);
			}
		}else{
			responseDto = new ResponseDto(false, "验证码不正确，请输入短信中收到的验证码");
		}
		return ResponseEntity.ok(responseDto);
	}
	
	@RequestMapping(path = "/doVerifRecomCode", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doVerifRecomCode(User user){
		return ResponseEntity.ok(userService.doVerifRecomCode(user));
	}
	
	@RequestMapping(path = "/doRegisterInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ResponseDto> doRegisterInfo(User user){
		return ResponseEntity.ok(userService.doRegisterInfo(user));
	}
	
	@RequestMapping(path = "/getAddressData", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<List<AddressDto>> getAddressData(HttpSession session){
		User user = (User) session.getAttribute(Constants.USER);
		if(user != null){
			return ResponseEntity.ok(userService.getAddressByUser(user));
		}
		return ResponseEntity.ok(new ArrayList<>());
	}
	
	@RequestMapping(path = "/doConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> doConfirm(String id){
		User user = userService.findUserById(id);
		user.setStatus(Constants.UserStatus.USER_STATUS_VALID.getCode());
		
		//生成推荐码
		Random random = new Random();
		String s = Integer.toString((int) random.nextInt(1000000));
		user.setRecomCode(s);
		
		ResponseDto doVerifRecomCode = userService.doVerifRecomCode(user);
		while(doVerifRecomCode.getSuccess()){
			user.setRecomCode(Integer.toString((int) random.nextInt(1000000)));
			doVerifRecomCode = userService.doVerifRecomCode(user);
		}
		userService.saveUser(user);
		return ResponseEntity.ok("");
	}

}
