package com.ydy.user.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ydy.order.model.Order;

public interface OrderJpaDao extends JpaRepository<Order, String> {

	int countByStatus(String status);

	@Query("select sum(t.agreeNum) from Order t where t.status = ? and date_format(t.createTime,'%Y-%m') like ? ")
	BigDecimal sumAgreeNumByStatus(String status, String createTime);
	
	@Query("select t from Order t where t.status = ? and date_format(t.createTime,'%Y-%m') like ? ")
	List<Order> findByStatusAndCreateTimeLike(String status, String createTime);
	
	@Query("select t from Order t where t.user.id = ? and t.status = ? and date_format(t.createTime,'%Y-%m') like ? ")
	List<Order> findByUserIdAndStatusAndCreateTimeLike(String userId, String status, String createTime);
	
	@Query("select t from Order t where t.status = ? and t.user.id = ? order by t.createTime desc ")
	List<Order> findByStatusAndUserId(String status, String userId);
	
	@Query("select t from Order t where t.status = ? order by t.createTime desc ")
	List<Order> findByStatus(String status);
	
	@Query("select sum(t.agreeAmt) from Order t where t.status = ? and date_format(t.createTime,'%Y-%m') like ?")
	BigDecimal sumAgreeAmtByStatus(String status, String createTime);

}
