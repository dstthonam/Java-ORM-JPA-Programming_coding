package com.springboot.jpa_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaModelApplication.class, args);
	}

}
