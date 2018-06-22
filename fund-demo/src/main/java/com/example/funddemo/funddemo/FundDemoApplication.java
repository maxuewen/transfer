package com.example.funddemo.funddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.example.funddemo.funddemo.mapper")
@EnableEurekaClient
@SpringBootApplication
public class FundDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundDemoApplication.class, args);
	}
}
