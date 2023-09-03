package com.backend.guhbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GuhbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuhbackendApplication.class, args);
	}

}
