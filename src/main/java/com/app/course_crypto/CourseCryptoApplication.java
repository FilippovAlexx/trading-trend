package com.app.course_crypto;

import com.app.course_crypto.service.BybitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseCryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseCryptoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(BybitService bybitService) {
		long end = System.currentTimeMillis();
		System.out.println(end);
		return args -> {
			bybitService.fetchInitialCandles();
		};
	}
}
