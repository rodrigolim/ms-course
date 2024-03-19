package com.developer.hrpayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StartHrPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartHrPayrollApplication.class, args);
	}

}
