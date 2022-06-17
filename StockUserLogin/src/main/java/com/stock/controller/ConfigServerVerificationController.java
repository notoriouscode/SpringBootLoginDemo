package com.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //it will get latest value of datasource every time.
public class ConfigServerVerificationController {

	@Value("${spring.datasource.url}") // get the value of this datasource. 
	private String dbUrl;
	
	@GetMapping(value="/read-config")
	public String readConfig() {
		return "DB URL = "+dbUrl;
	}
}
