package com.ydy.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ydy.user.model.User;

public interface UserJpaDao extends JpaRepository<User, String> {

	User findByUserMobileAndDeleteFlag(String userMobile, String deleteFlag);

	User findByRecomCodeAndDeleteFlag(String recomCode, String deleteFlag);
	
	User findByOpenidAndDeleteFlag(String openid, String deleteFlag);

	List<User> findByUserTypeAndStatusAndDeleteFlag(String userType, String status, String deleteFlag);

	@Query("select count(*) from User t where t.userType = ? and t.status = ? and date_format(t.createTime,'%Y-%m') like ? and t.deleteFlag = ?")
	int getCountPartnerByMonth(String userType, String status, String createTime, String deleteFlag);
	
	List<User> findByReferrerUserIdAndDeleteFlag(String id, String deleteFlag);
	
	int countByReferrerUserIdAndDeleteFlag(String id, String deleteFlag);

}
