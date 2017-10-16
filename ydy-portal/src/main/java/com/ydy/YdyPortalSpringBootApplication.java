package com.ydy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@ComponentScan(basePackages="com.ydy.*.model")
public class YdyPortalSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(YdyPortalSpringBootApplication.class, args);
	}
}
