package com.ydy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ydy.user.model.Address;

public interface AddressJpaDao extends JpaRepository<Address, String>{

	
}
