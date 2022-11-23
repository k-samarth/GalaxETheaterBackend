package com.theatre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theatre.entity.Address;
import com.theatre.entity.Theatre;


public interface TheatreService {
	public String saveTheatre(Theatre theatre);
	public String updateTheatre(Theatre theatre);
	public String deleteTheatre(int  theatreId);
	public String saveAddress(Address address);
	public List<Theatre> getTheatres();
	public boolean isIdExists(int id);
	public List<Theatre> getByCity(String city);
	public List<Theatre> getByName(String name);
}
