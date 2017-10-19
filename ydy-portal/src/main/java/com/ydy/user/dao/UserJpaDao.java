package com.ydy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ydy.user.model.User;

public interface UserJpaDao extends JpaRepository<User, String>{
	
	User findByUserMobileAndDeleteFlag(String userMobile,String deleteFlag);
	
	User findByRecomCodeAndDeleteFlag(String recomCode,String deleteFlag);

}
