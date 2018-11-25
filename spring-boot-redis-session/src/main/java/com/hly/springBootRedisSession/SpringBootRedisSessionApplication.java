package com.hly.springBootRedisSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableCaching//开启全局缓存
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)//开启session共享，session有效期30分钟
public class SpringBootRedisSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisSessionApplication.class, args);
	}
}
