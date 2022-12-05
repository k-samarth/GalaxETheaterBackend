package com.theatre;

// imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Starting Point for The Backend Application
@EnableEurekaClient
@SpringBootApplication
public class TheaterApplication {

//	Function to run the Application
	public static void main(String[] args) {
		SpringApplication.run(TheaterApplication.class, args);
		System.out.println("GalaxE Theater Application has Started");
	}

}
