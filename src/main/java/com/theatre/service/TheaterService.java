package com.theatre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theatre.dto.RowDTO;
import com.theatre.dto.TheaterDTO;
import com.theatre.entity.Address;
import com.theatre.entity.Row;
import com.theatre.entity.Theater;
import com.theatre.exception.NoContentException;
import com.theatre.exception.TheaterAlreadyExistException;
import com.theatre.exception.TheaterNotFoundException;


public interface TheaterService {
	public String saveTheater(TheaterDTO theaterDto);
	public String validateAndSaveTheater(TheaterDTO theaterDto) throws TheaterAlreadyExistException;
	public String validateAndUpdateTheater(TheaterDTO theaterDto) throws TheaterNotFoundException;
	public String updateTheater(TheaterDTO theaterDto);
	public String deleteTheater(int  theaterId);
	public List<TheaterDTO> getTheaters() throws NoContentException;
	public boolean isIdExists(int id);
	public List<TheaterDTO> getByCity(String city) throws NoContentException;
	public RowDTO findByRowname(String name);
	public TheaterDTO findByname(String name);
	public List<TheaterDTO> getByAddress(String input)throws NoContentException;
	public TheaterDTO validateAndFind(String name) throws NoContentException;
	
}
