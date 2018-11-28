package com.hly.springBootSecurityMybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hly.springBootSecurityMybatis.dao")
public class SpringBootSecurityMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityMybatisApplication.class, args);
	}
}
