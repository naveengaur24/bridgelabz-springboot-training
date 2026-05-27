package com.example.shopsmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShopsmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopsmartApplication.class, args);
	}
}