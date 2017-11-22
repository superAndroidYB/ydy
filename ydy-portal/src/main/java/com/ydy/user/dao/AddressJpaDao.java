package com.ydy.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ydy.user.model.Address;

public interface AddressJpaDao extends JpaRepository<Address, String>{
	
	List<Address> findByUserId(String userId);

	
}
