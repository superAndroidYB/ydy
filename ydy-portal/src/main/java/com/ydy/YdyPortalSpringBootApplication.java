package com.ydy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class YdyPortalSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(YdyPortalSpringBootApplication.class, args);
	}
}
