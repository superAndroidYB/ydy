package com.ydy.user.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ydy.order.model.Order;
import com.ydy.user.model.User;

public interface OrderJpaDao extends JpaRepository<Order, String> {

	int countByStatus(String status);

	@Query("select sum(t.agreeNum) from Order t where t.status = ? and t.createTime like ? ")
	BigDecimal sumAgreeNumByStatus(String status, String createTime);

	List<Order> findByStatusAndCreateTimeLike(String status, String createTime);
	
	@Query("select t from Order t where t.status = ? and t.user.id = ? order by t.createTime desc ")
	List<Order> findByStatusAndUserId(String status, String userId);
	
	@Query("select sum(t.agreeAmt) from Order t where t.status = ? and t.createTime like ?")
	BigDecimal sumAgreeAmtByStatus(String status, String createTime);

}
