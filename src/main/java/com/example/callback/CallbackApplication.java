package com.example.callback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallbackApplication.class, args);
	}

}
