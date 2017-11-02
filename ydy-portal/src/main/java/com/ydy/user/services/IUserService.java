package com.ydy.user.services;

import java.util.List;

import com.ydy.dto.IndexDto;
import com.ydy.dto.ResponseDto;
import com.ydy.user.model.User;

public interface IUserService {
	public static final String BEAN_ID = "ydy.UserService";
	
	public boolean checkMobileExtis(String mobile);
	
	public User doUserRegister(User user);
	
	public User findUserById(String id);
	
	public ResponseDto doRegisterInfo(User user);
	
	public ResponseDto doVerifRecomCode(User user);
	
	/**
	 * 获取所有未确认合伙人
	 * @return
	 */
	public List<User> getAllUndeterminedUser();
	
	public List<User> getValidUserList();
	
	public IndexDto getIndexData();
	

}
