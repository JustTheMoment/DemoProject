package com.d11.whitedevil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WhitedevilApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhitedevilApplication.class, args);
	}

}
