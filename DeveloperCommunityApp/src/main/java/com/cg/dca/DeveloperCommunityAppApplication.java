package com.cg.dca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DeveloperCommunityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperCommunityAppApplication.class, args);
	}

}