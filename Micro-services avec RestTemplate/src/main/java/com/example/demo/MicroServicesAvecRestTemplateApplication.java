package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer  // Active les fonctionnalités du serveur Eureka
@SpringBootApplication
public class MicroServicesAvecRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesAvecRestTemplateApplication.class, args);
	}

}
