package com.javahly.springbootsecurityjson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javahly.springbootsecurityjson.dao")
public class SpringBootSecurityJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJsonApplication.class, args);
	}

}
