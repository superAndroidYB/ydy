package com.ydy.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.user.dao.AddressJpaDao;
import com.ydy.user.services.IAddressService;

@Service(IAddressService.BEAN_ID)
public class AddressServiceImpl implements IAddressService {
	
	//@Autowired
	public AddressJpaDao addressDao;
	

	
	

}
