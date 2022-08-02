package com.api.alvaro.lembrina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class LembrinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LembrinaApplication.class, args);
	}
}