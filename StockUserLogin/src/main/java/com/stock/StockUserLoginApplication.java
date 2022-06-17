package com.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockUserLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockUserLoginApplication.class, args);
	}

}
