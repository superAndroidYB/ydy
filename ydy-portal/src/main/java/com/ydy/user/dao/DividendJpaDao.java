package com.ydy.user.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ydy.order.model.Dividend;

public interface DividendJpaDao extends JpaRepository<Dividend, String>{
	
	List<Dividend> findByUserIdOrderByCreateTimeDesc(String userId);
	
	@Query("select date_format(t.createTime,'%Y-%m') as month from Dividend t group by date_format(t.createTime,'%Y-%m')")
	List<String> getMonths();
	
	
	@Query("select sum(t.amt) from Dividend t where t.user.id = ? and t.subject = ? and date_format(t.createTime,'%Y-%m') like ?")
	BigDecimal getSumAmtByUser(String userId, String subject, String month);
	
	@Query("select sum(t.amt) from Dividend t where date_format(t.createTime,'%Y-%m') like ?")
	BigDecimal getSumAmt(String month);
	
	
	List<Dividend> findByUserIdAndSubjectOrderByCreateTimeDesc(String userId,String subject);
	

}
