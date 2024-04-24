package com.developer.hruser;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLOutput;

@SpringBootApplication
@OpenAPIDefinition
public class StartHrUserApplication implements CommandLineRunner {


	@Autowired
 	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(StartHrUserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//	System.out.println("encode = "+passwordEncoder.encode("123456"));
	}
}
