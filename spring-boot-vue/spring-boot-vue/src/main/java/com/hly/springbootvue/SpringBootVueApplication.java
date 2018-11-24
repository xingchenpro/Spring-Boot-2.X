package com.hly.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hly.springbootvue.dao")
public class SpringBootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}
}
