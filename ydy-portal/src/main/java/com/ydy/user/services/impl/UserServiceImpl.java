package com.ydy.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.services.IUserService;

@Service(value = IUserService.BEAN_ID)
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserJpaDao userDao;
	
	
}
