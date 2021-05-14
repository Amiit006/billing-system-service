package com.rr.particularservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParticularServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParticularServiceApplication.class, args);
	}

}
