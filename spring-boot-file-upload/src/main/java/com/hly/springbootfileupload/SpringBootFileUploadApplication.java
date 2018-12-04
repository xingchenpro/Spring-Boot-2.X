package com.hly.springbootfileupload;

import com.hly.springbootfileupload.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFileUploadApplication.class, args);
	}


}
