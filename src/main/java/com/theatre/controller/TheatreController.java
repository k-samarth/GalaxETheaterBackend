package com.theatre.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.entity.Address;
import com.theatre.entity.Theatre;
import com.theatre.service.TheatreService;

@RestController
@RequestMapping("theatre")
@CrossOrigin
public class TheatreController {
	
	@Autowired
	TheatreService theatreService;
	
	@GetMapping
	public ResponseEntity<List<Theatre>> getTheaters(){
		List<Theatre> theatres = theatreService.getTheatres();
		
		ResponseEntity<List<Theatre>> responseEntity;

		if(theatres.isEmpty()) {
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping                 
	public ResponseEntity<String> saveTheatre(@RequestBody Theatre theatre) {    
		ResponseEntity<String> responseEntity;            
		int id = theatre.getId();
		if(theatreService.isIdExists(id)) {      
			responseEntity = new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);      
		}        
		else         
		{             
			String message = theatreService.saveTheatre(theatre);       
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);     
			
		}         
			return responseEntity;    
	}
	@PostMapping("save")              
	public ResponseEntity<String> saveAddress(@RequestBody Address address) {    
		ResponseEntity<String> responseEntity;            
		int id = address.getId();
		if(theatreService.isIdExists(id)) {      
			responseEntity = new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);      
		}        
		else         
		{             
			String message = theatreService.saveAddress(address);       
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);      
		}         
			return responseEntity;    
	}
	
	@PutMapping                 
	public ResponseEntity<String> updateTheatre(@RequestBody Theatre theatre) {    
		ResponseEntity<String> responseEntity;            
		int id = theatre.getId();
		if(!theatreService.isIdExists(id)) {      
			responseEntity = new ResponseEntity<String>("User does not exists", HttpStatus.CONFLICT);      
		}        
		else         
		{             
			String message = theatreService.saveTheatre(theatre);       
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);     
			
		}         
			return responseEntity;    
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        ResponseEntity<String> responseEntity;
        if (theatreService.isIdExists(id)) {
            String message = theatreService.deleteTheatre(id);
            responseEntity = new ResponseEntity<String>(message,
                    HttpStatus.OK);
        } else {
            String errorMessage = "Theatre with this ID doesnt Exists";
            responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }
	
	@GetMapping("city/{city}")
	public ResponseEntity<List<Theatre>> getTheatersByCity(@PathVariable("city") String city){
		List<Theatre> theatres = theatreService.getByCity(city);
		
		ResponseEntity<List<Theatre>> responseEntity;

		if(theatres.isEmpty()) {
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<List<Theatre>> getTheatersByName(@PathVariable("name") String name){
		List<Theatre> theatres = theatreService.getByName(name);
		
		ResponseEntity<List<Theatre>> responseEntity;

		if(theatres.isEmpty()) {
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Theatre>>(theatres, HttpStatus.OK);
		}
		return responseEntity;
	}

}
