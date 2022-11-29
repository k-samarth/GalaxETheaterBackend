package com.theatre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theatre.entity.Address;
import com.theatre.entity.Row;
import com.theatre.entity.Theater;
import com.theatre.exception.NoContentException;
import com.theatre.exception.UserAlreadyExistException;


public interface TheaterService {
	public String saveTheater(Theater theater);
	public String validateAndSaveTheater(Theater theater) throws UserAlreadyExistException;
	public String updateTheater(Theater theater);
	public String deleteTheater(int  theaterId);
	public List<Theater> getTheaters() throws NoContentException;
	public boolean isIdExists(int id);
	public List<Theater> getByCity(String city);
	public Row findByRowname(String name);
	public Theater findByname(String name);
	public List<Theater> getByAddress(String input)throws NoContentException;
	
}
