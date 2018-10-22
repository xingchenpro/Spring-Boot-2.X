package com.hly.springBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@MapperScan("com.hly.springBoot.dao")
@SpringBootApplication
public class SpringBootAngular1JspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngular1JspApplication.class, args);
	}
}
