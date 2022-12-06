package com.theatre.controller;

// util imports
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;

//import org.apache.log4j.Logger;

//springframework imports
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

import com.theatre.dto.TheaterDTO;
//project imports
import com.theatre.entity.Address;
import com.theatre.entity.Row;
import com.theatre.entity.Seat;
import com.theatre.entity.SeatBooking;
import com.theatre.entity.Theater;
import com.theatre.exception.NoContentException;
import com.theatre.exception.TheaterAlreadyExistException;
import com.theatre.exception.TheaterNotFoundException;
import com.theatre.service.TheaterService;

// Controller --> ServiceImpl [Service]
@RestController
@RequestMapping("theater")
@CrossOrigin
public class TheaterController {

	@Autowired
	TheaterService theaterService;
	

//	Logger SetUp
//	private static Logger theaterLogger = Logger.getLogger(TheaterController.class);

	
	/*
	 * |--------API to Fetch All Theater Data--------|
	 * Call : 
	 *  	GET : "http://localhost:9090/theater/All"
	*/
	
	@GetMapping("/All")
	public ResponseEntity<List<TheaterDTO>> GET() {
		
	/*
	 * 	Pseudo Code
	 * 		1. Create Response Entity Object
	 * 		2. Try fetching all theaters from Service Implementation
	 * 		3. if Successful send proper response with response code
	 * 		3. Catch and send proper Response Code																					
	 */
		ResponseEntity<List<TheaterDTO>> responseEntity;
		try{
			responseEntity = new ResponseEntity<List<TheaterDTO>>(theaterService.getTheaters(), HttpStatus.OK);
//			theaterLogger.info("Successful Retrieval of Theater Details");			
		}
		catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getCode()+" : "+e.getMessage(),HttpStatus.BAD_REQUEST);
//			theaterLogger.info(e.getCode()+" : "+e.getMessage());
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
//			theaterLogger.info(e.getMessage());
		}
		return responseEntity;
	}       
	
	
	
	/*
	 * |--------API to post Theater Data into Database--------|
	 * Call : 
	 *  	POST : "http://localhost:9090/theater"
	*/
	@PostMapping
	public ResponseEntity<String> POST(@RequestBody TheaterDTO theater) {
		
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try saving theaters through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */
		
		ResponseEntity<String> responseEntity;	

		try {
			responseEntity = new ResponseEntity<String>(theaterService.validateAndSaveTheater(theater),HttpStatus.OK);
//			theaterLogger.info("Theater Data has been successfully stored");
		}
		catch (TheaterAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>(e.getCode()+" : "+e.getMessage(), HttpStatus.OK);
			//theaterLogger.info(e.getCode()+" : "+e.getMessage());
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			//theaterLogger.error(e.getMessage());
		}
		return responseEntity;
	}


	/*
	 * |--------API to update Theater Data into Database--------|
	 * Call : 
	 *  	PUT : "http://localhost:9090/theater/update"
	*/
	
	@PutMapping("/update")
public ResponseEntity<String> UPDATE(@RequestBody TheaterDTO theater) {
		
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try update theaters through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */
		
		
	    ResponseEntity<String> responseEntity;	
		try {
			responseEntity = new ResponseEntity<String>(theaterService.validateAndUpdateTheater(theater),HttpStatus.OK);
			//theaterLogger.info("Theater Data has been successfully stored");
			}
		catch (TheaterNotFoundException e) 
			{
			responseEntity = new ResponseEntity<String>(e.getCode()+" : "+e.getMessage(), HttpStatus.OK);
			//theaterLogger.error(e.getCode()+" : "+e.getMessage());
			}
		catch (Exception e) 
			{
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			//theaterLogger.error(e.getMessage());
			}
	    return responseEntity;
}

	
	/*
	 * |--------API to delete Theater Data into Database--------|
	 * Call : 
	 *  	PUT : "http://localhost:9090/theater/{name}"
	*/
	@DeleteMapping("{name}")
	public ResponseEntity<String> DELETE(@PathVariable("name") String name) {
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try deleting theaters through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */
		ResponseEntity<String> responseEntity;
		TheaterDTO theaterdetails = theaterService.findByname(name);
		int id = -1;
		if (theaterdetails != null) {
			id = theaterdetails.getId();
		}
		if (id!=-1 && theaterService.isIdExists(id)) {
			String message = theaterService.deleteTheater(id);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			String errorMessage = "Theater with this ID doesnt Exists";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	

	/*
	 * |--------API to find theater by city--------|
	 * Call : 
	 *  	PUT : "http://localhost:9090/theater/city/{city}"
	*/
@GetMapping("city/{city}")
public ResponseEntity<?> GETBYCITY(@PathVariable("city") String city) {
	/*
	 * 	Pseudo Code
	 * 		1. Create Response Entity Object
	 * 		2. Try find theaters by city through Service Implementation
	 * 		3. if Successful send proper response with response code
	 * 		4. Catch and send proper Response Code																					
	 */
	
	List<TheaterDTO> theatres = new ArrayList<TheaterDTO>();
	ResponseEntity<?> responseEntity;
	try {
		theatres = theaterService.getByCity(city);
		responseEntity = new ResponseEntity<List<TheaterDTO>>(theatres, HttpStatus.OK);
		//theaterLogger.info("Theater Data has been successfully displayed");
		}
	catch(NoContentException e) {
		responseEntity = new ResponseEntity<String>(e.getCode()+":"+e.getMessage(), HttpStatus.BAD_REQUEST);
		 //theaterLogger.error(e.getCode()+" : "+e.getMessage());
		 }
	catch (Exception e) {
		responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
		//theaterLogger.error(e.getMessage());
		}
	return responseEntity;
}


	
/*
 * |--------API to find theater by name--------|
 * Call : 
 *  	PUT : "http://localhost:9090/theater/name/{name}"
*/
	@GetMapping("name/{name}")



	   public ResponseEntity<List<TheaterDTO>> GETBY(@PathVariable("name") String name) {
		
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try find theaters by name through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */
			List<TheaterDTO> theaterServiceTheaters = new ArrayList<TheaterDTO>();
	        ResponseEntity<List<TheaterDTO>> responseEntity;
	        try{
	        	TheaterDTO theater_i = theaterService.validateAndFind(name);
	        	theaterServiceTheaters.add(theater_i);	            
	        	responseEntity = new ResponseEntity<List<TheaterDTO>>(theaterServiceTheaters, HttpStatus.OK);
	            //theaterLogger.info("Successful Retrieval of Theater Details");            
	        }
	        catch (NoContentException e) {
	            responseEntity = new ResponseEntity(e.getCode()+" : "+e.getMessage(),HttpStatus.BAD_REQUEST);
	            //theaterLogger.error(e.getCode()+" : "+e.getMessage());
	        }
	        catch (Exception e) {
	            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
	            //theaterLogger.error(e.getMessage());
	        }
	        return responseEntity;
	        
	    
	    }
	

	
	/*
	 * |--------API to find theater by address--------|
	 * Call : 
	 *  	PUT : "http://localhost:9090/theater/address/{input}"
	*/
	@GetMapping("address/{input}")
    public  ResponseEntity<List<TheaterDTO>>GETTHEATERBYADDRESS(@PathVariable("input") String input)throws NoContentException {
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try find theaters by address through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */

		ResponseEntity<List<TheaterDTO>> responseEntity;
		try{
			responseEntity = new ResponseEntity<List<TheaterDTO>>(theaterService.getByAddress(input), HttpStatus.OK);
			//theaterLogger.info("Successful Retrieval of Theater Address");			
		}
		catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getCode()+" : "+e.getMessage(),HttpStatus.BAD_REQUEST);
			//theaterLogger.error(e.getCode()+" : "+e.getMessage());
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			//theaterLogger.error(e.getMessage());
		}
		return responseEntity;
	}
}
