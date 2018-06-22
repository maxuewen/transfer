package com.example.paydemo.paydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.example.paydemo.paydemo.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class PayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayDemoApplication.class, args);
	}
}
