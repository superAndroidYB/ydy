package com.ydy.user.services.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.dto.IndexDto;
import com.ydy.dto.ResponseDto;
import com.ydy.user.dao.AddressJpaDao;
import com.ydy.user.dao.DividendJpaDao;
import com.ydy.user.dao.OrderJpaDao;
import com.ydy.user.dao.UserJpaDao;
import com.ydy.user.model.Address;
import com.ydy.user.model.AddressDto;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;

@Service(value = IUserService.BEAN_ID)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserJpaDao userDao;
	@Autowired
	private AddressJpaDao addressDao;
	@Autowired
	private OrderJpaDao orderDao;
	@Autowired
	private DividendJpaDao dividendDao;

	
	@Override
	public User saveUser(User user) {
		if(StringUtils.isBlank(user.getId())){
			user.setId(UUID.randomUUID().toString());
			user.setDeleteFlag(Constants.NO);
		}
		return userDao.save(user);
	}
	
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
	public User findUserByOpenid(String openid) {
		return userDao.findByOpenidAndDeleteFlag(openid, Constants.NO);
	}

	@Override
	public ResponseDto doRegisterInfo(User user) {
		ResponseDto verifRes = doVerifRecomCode(user);
		if(verifRes != null){
			return verifRes;
		}
		
		User userDB = userDao.findOne(user.getId());
		userDB.setUserName(user.getUserName());
		userDB.setRecomCode("");
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
			}
			//else{
				//return new ResponseDto(true, String.format("推荐人为%s，请确认无误后继续！", userDB.getUserName()));
			//}
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

	@Override
	public IndexDto getIndexData() {
		IndexDto result = new IndexDto();
		int countByStatus = orderDao.countByStatus(Constants.OrderStatus.ORDER_APPLY.getCode());
		result.setOrderMsg(countByStatus > 0 ? String.format("%s个申请待处理", countByStatus) : "点击查看详情");
		List<User> findByUserTypeAndStatusAndDeleteFlag = userDao.findByUserTypeAndStatusAndDeleteFlag(Constants.UserType.USER_TYPE_PARTNER.getCode()
				, Constants.UserStatus.USER_STATUS_REGISTER.getCode(),Constants.NO);
		result.setPartnerMsg(findByUserTypeAndStatusAndDeleteFlag.size() > 0 ? String.format("%s个申请待处理", findByUserTypeAndStatusAndDeleteFlag.size()) : "点击查看详情");
		result.setMonth(DateFormatUtils.format(new Date(), "yyyy年MM月"));
		result.setGetTime(DateFormatUtils.format(new Date(), "yyyy年MM月dd日 hh:mm:ss"));
		String month = DateFormatUtils.format(new Date(), "yyyy-MM");
		BigDecimal sumAgreeNumByStatus = orderDao.sumAgreeNumByStatus(Constants.OrderStatus.ORDER_CONFIRM.getCode(),month+"%");
		result.setShippingQty(new DecimalFormat(",###").format(sumAgreeNumByStatus == null ? BigDecimal.ZERO : sumAgreeNumByStatus));
		BigDecimal sumAgreeAmtByStatus = orderDao.sumAgreeAmtByStatus(Constants.OrderStatus.ORDER_CONFIRM.getCode(),month+"%");
		result.setSaleAmt(new DecimalFormat(",###").format(sumAgreeAmtByStatus == null ? BigDecimal.ZERO : sumAgreeAmtByStatus));
		int countPartnerByMonth = userDao.getCountPartnerByMonth(Constants.UserType.USER_TYPE_PARTNER.getCode()
				, Constants.UserStatus.USER_STATUS_VALID.getCode()
				, month +"%", Constants.NO);
		result.setAddPartnerNum(Integer.toString(countPartnerByMonth));
		BigDecimal dAmt = dividendDao.getSumAmt(month);
		result.setDividendAmt(new DecimalFormat(",###").format(dAmt == null ? BigDecimal.ZERO : dAmt));
		return result;
	}

	@Override
	public List<User> getNextUserList(User user) {
		List<User> list = userDao.findByReferrerUserIdAndDeleteFlag(user.getId(), Constants.NO);
		for (User user2 : list) {
			user2.setNextNum(userDao.countByReferrerUserIdAndDeleteFlag(user2.getId(), Constants.NO));
		}
		return list;
	}

	@Override
	public int getNextUserNum(User user) {
		return userDao.countByReferrerUserIdAndDeleteFlag(user.getId(), Constants.NO);
	}

	@Override
	public List<AddressDto> getAddressByUser(User user) {
		List<AddressDto> dtoList = new ArrayList<>();
		List<Address> findByUserId = addressDao.findByUserId(user.getId());
		for (Address address : findByUserId) {
			AddressDto e = new AddressDto();
			e.setId(address.getId());
			e.setText(address.getAddress());
			dtoList.add(e);
		}
		return dtoList;
	}

	

	

}
