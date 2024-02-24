package com.kbtg.bootcamp.posttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;

@SpringBootApplication
public class PosttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosttestApplication.class, args);
	}

	@Bean
	LotteryService lotteryService() {
		return new LotteryService();
	}
}
