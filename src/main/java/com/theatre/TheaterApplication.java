package com.theatre;

// imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.theatre.mapper.RowDTOMapper;
import com.theatre.mapper.RowDTOMapperImpl;
import com.theatre.mapper.TheaterDTOMapper;
import com.theatre.mapper.TheaterDTOMapperImpl;
import com.theatre.mapper.TheaterListDTOMapper;
import com.theatre.mapper.TheaterListDTOMapperImpl;

// Starting Point for The Backend Application
@SpringBootApplication
public class TheaterApplication {

//	Function to run the Application
	public static void main(String[] args) {
		SpringApplication.run(TheaterApplication.class, args);
		System.out.println("GalaxE Theater Application has Started");
		
	}

	/*
	 * Pseudo Code
	 * Bean creation for the mappers
	 */	@Bean
	public TheaterDTOMapper getTheaterDTOMapper() {
		return new TheaterDTOMapperImpl();
	}
	@Bean
	public TheaterListDTOMapper getTheaterListDTOMapper() {
		return new TheaterListDTOMapperImpl();
	}
	@Bean
	public RowDTOMapper getRowDTOMapper() {
		return new RowDTOMapperImpl();
	}
}
