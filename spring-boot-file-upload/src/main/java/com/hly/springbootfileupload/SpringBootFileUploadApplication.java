package com.hly.springbootfileupload;

import com.hly.springbootfileupload.storage.StorageProperties;
import com.hly.springbootfileupload.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//参考 ： https://spring.io/guides/gs/uploading-files/
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFileUploadApplication.class, args);
	}

	//初始化，创建文件夹
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
