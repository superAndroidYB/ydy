package com.ydy.user.services;

import com.ydy.dto.ResponseDto;
import com.ydy.user.model.User;

public interface IUserService {
	public static final String BEAN_ID = "ydy.UserService";
	
	public boolean checkMobileExtis(String mobile);
	
	public User doUserRegister(User user);
	
	public User findUserById(String id);
	
	public ResponseDto doRegisterInfo(User user);
	
	public ResponseDto doVerifRecomCode(User user);

}
