package com.fap.cinanhalam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CinanhalamApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinanhalamApplication.class, args);
	}

}
