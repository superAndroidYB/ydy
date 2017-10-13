package com.ydy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {
	
	@Autowired
    private Environment env;

	@Bean(name = "dataSource")
	public DruidDataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setMaxActive(env.getProperty("spring.datasource.druid.max-active", Integer.class));
		dataSource.setMinIdle(env.getProperty("spring.datasource.druid.min-idle", Integer.class));
		dataSource.setInitialSize(env.getProperty("spring.datasource.druid.initial-size", Integer.class));
		return dataSource;
	}

}
