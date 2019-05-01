package com.hly.springBootRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableCaching//开启全局缓存
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)//开启session共享，session有效期30分钟
public class SpringBootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}


}

//https://blog.csdn.net/moshowgame/article/details/80792774
//http://www.ityouknow.com/springboot/2016/03/06/spring-boot-redis.html