package com.theatre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatre.entity.Address;
import com.theatre.entity.Theatre;
import com.theatre.repository.AddressRepository;
import com.theatre.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService{
	
	
	

	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	
	
	
	@Override
	public String saveTheatre(Theatre theatre) {
		// TODO Auto-generated method stub
		theatreRepository.save(theatre);
		
		
		return "Saved Successfully";
	}

	@Override
	public boolean isIdExists(int id) {
		// TODO Auto-generated method stub
		Optional<Theatre> theatre = theatreRepository.findById(id);
		return theatre.isPresent();
	}

	@Override
	public String saveAddress(Address address) {
		// TODO Auto-generated method stub
		addressRepository.save(address);
		return "Saved Successfully";
	}

	@Override
	public List<Theatre> getTheatres() {
		// TODO Auto-generated method stub
		return (List<Theatre>) theatreRepository.findAll();
	}

	@Override
	public String updateTheatre(Theatre theatre) {
		theatreRepository.save(theatre);
		return "Updated Successfully";
	}

	@Override
	public String deleteTheatre(int theatreId) {
		// TODO Auto-generated method stub
		theatreRepository.deleteById(theatreId);
		return "Deleted Successfully";
	}

	@Override
	public List<Theatre> getByCity(String city) {
		// TODO Auto-generated method stub
		return theatreRepository.findByCity(city);
	}

	@Override
	public List<Theatre> getByName(String name) {
		// TODO Auto-generated method stub
		return theatreRepository.findByName(name);
	}

	

}
