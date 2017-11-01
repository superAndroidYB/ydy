package com.ydy.user.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.dto.ResponseDto;
import com.ydy.user.dao.AddressJpaDao;
import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.Address;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;

@Service(value = IUserService.BEAN_ID)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserJpaDao userDao;
	@Autowired
	private AddressJpaDao addressDao;

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

	@Override
	public User findUserById(String id) {
		return userDao.getOne(id);
	}

	@Override
	public ResponseDto doRegisterInfo(User user) {
		ResponseDto verifRes = doVerifRecomCode(user);
		if(verifRes != null){
			return verifRes;
		}
		
		User userDB = userDao.findOne(user.getId());
		userDB.setUserName(user.getUserName());
		userDB.setRecomCode(user.getRecomCode());
		userDB.setStatus(Constants.UserStatus.USER_STATUS_REGISTER.getCode());
		
		//保存地址
		Address address = new Address();
		address.setId(UUID.randomUUID().toString());
		address.setUser(userDB);
		address.setAddress(user.getAddress());
		address.setIsDefault(Constants.YES);
		addressDao.save(address);
		
		//保存上下级关系
		if(StringUtils.isNotBlank(user.getRecomCode())){
			String recomCode = StringUtils.trim(user.getRecomCode());
			User recomUser = userDao.findByRecomCodeAndDeleteFlag(recomCode, Constants.NO);
			userDB.setUserLevel(recomUser.getUserLevel() + 1);
			userDB.setReferrerUser(recomUser);
			userDB.setRootUser(getRootUser(recomUser));
		}
		
		ResponseDto result = new ResponseDto(true, "注册成功");
		result.setData(userDao.save(userDB));
		return result;
	}
	/**
	 * 递归获取根用户
	 * @param user
	 * @return
	 */
	private User getRootUser(User user){
		if(user.getReferrerUser() == null){
			return user;
		}else{
			return getRootUser(user);
		}
	}

	@Override
	public ResponseDto doVerifRecomCode(User user) {
		if(user != null && StringUtils.isNotBlank(user.getRecomCode())){
			User userDB = userDao.findByRecomCodeAndDeleteFlag(user.getRecomCode(), Constants.NO);
			if(userDB == null){
				return new ResponseDto(false, "您填写的推荐码系统中不存在，请确认是否输入正确，如没有推荐人，请不要填写！");
			}else{
				return new ResponseDto(true, String.format("推荐人为%s，请确认无误后继续！", userDB.getUserName()));
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUndeterminedUser() {
		return userDao.findByUserTypeAndStatusAndDeleteFlag(Constants.UserType.USER_TYPE_PARTNER.getCode()
				,Constants.UserStatus.USER_STATUS_REGISTER.getCode(),Constants.NO);
	}

	@Override
	public List<User> getValidUserList() {
		return userDao.findByUserTypeAndStatusAndDeleteFlag(Constants.UserType.USER_TYPE_PARTNER.getCode()
				,Constants.UserStatus.USER_STATUS_VALID.getCode(),Constants.NO);
	}

}
