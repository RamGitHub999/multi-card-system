package com.mbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class MultiBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiBankingSystemApplication.class, args);
	}

}

