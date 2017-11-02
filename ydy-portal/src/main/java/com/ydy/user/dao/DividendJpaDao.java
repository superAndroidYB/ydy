package com.ydy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ydy.order.model.Dividend;

public interface DividendJpaDao extends JpaRepository<Dividend, String>{

}
