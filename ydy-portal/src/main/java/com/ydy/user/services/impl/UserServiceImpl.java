package com.ydy.user.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;

@Service(value = IUserService.BEAN_ID)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserJpaDao userDao;

	@Override
	public boolean checkMobileExtis(String mobile) {
		User user = userDao.findByUserMobileAndDeleteFlag(mobile, Constants.NO);
		return user != null;
	}

	@Override
	public User doUserRegister(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setDeleteFlag(Constants.NO);
		userDao.save(user);
		return user;
	}

}
