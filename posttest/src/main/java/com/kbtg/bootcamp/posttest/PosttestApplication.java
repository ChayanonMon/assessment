package com.kbtg.bootcamp.posttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PosttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosttestApplication.class, args);
	}

}
