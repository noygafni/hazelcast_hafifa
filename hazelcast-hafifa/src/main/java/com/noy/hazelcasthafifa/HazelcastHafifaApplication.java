package com.noy.hazelcasthafifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.noy")
public class HazelcastHafifaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastHafifaApplication.class, args);
	}

}
