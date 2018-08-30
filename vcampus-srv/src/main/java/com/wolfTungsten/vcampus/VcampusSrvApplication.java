package com.wolfTungsten.vcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VcampusSrvApplication {

	public static void main(String[] args) {
		ORM orm = ORM.getInstance();
		SpringApplication.run(VcampusSrvApplication.class, args);
	}
}
