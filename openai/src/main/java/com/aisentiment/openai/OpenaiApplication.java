package com.aisentiment.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.aisentiment.openai.controller")
public class OpenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenaiApplication.class, args);
		System.out.println("application starts simply!!!!");
	}

}
