package com.theatre.controller;

// util imports
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;

import org.apache.log4j.Logger;

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

//project imports
import com.theatre.entity.Address;
import com.theatre.entity.Row;
import com.theatre.entity.Seat;
import com.theatre.entity.SeatBooking;
import com.theatre.entity.Theater;
import com.theatre.exception.NoContentException;
import com.theatre.exception.UserAlreadyExistException;
import com.theatre.exception.UserNotFoundException;
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
	public ResponseEntity<List<Theater>> GET() {
		
	/*
	 * 	Pseudo Code
	 * 		1. Create Response Entity Object
	 * 		2. Try fetching all theaters from Service Implementation
	 * 		3. if Successful send proper response with response code
	 * 		3. Catch and send proper Response Code																					
	 */
		ResponseEntity<List<Theater>> responseEntity;
		try{
			responseEntity = new ResponseEntity<List<Theater>>(theaterService.getTheaters(), HttpStatus.OK);
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
	public ResponseEntity<String> POST(@RequestBody Theater theater) {
		
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
		catch (UserAlreadyExistException e) {
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
public ResponseEntity<String> UPDATE(@RequestBody Theater theater) {
	    ResponseEntity<String> responseEntity;	
		try {
			responseEntity = new ResponseEntity<String>(theaterService.validateAndUpdateTheater(theater),HttpStatus.OK);
			//theaterLogger.info("Theater Data has been successfully stored");
			}
		catch (UserNotFoundException e) 
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

	
	
	//	 performing deletion of inactive theaters using theatre name.
	@DeleteMapping("{name}")
	public ResponseEntity<String> DELETE(@PathVariable("name") String name) {
		ResponseEntity<String> responseEntity;
		Theater theaterdetails = theaterService.findByname(name);

//		checking whether the theater is empty or not(if empty id=-1 if theater is present id will be fetched from the database)
		int id = -1;
		if (theaterdetails != null) {
			id = theaterdetails.getId();
		}

		
//         checking whether the id exists in the database or not
		if (id!=-1 && theaterService.isIdExists(id)) {
//        	 deletion function is activated.
			String message = theaterService.deleteTheater(id);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
//        	 deletion function is not activated No content is returned..
			String errorMessage = "Theater with this ID doesnt Exists";
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}


	

//	 performing get theater by city operation
@GetMapping("city/{city}")
public ResponseEntity<?> GETBYCITY(@PathVariable("city") String city) {
	List<Theater> theatres = new ArrayList<Theater>();
	ResponseEntity<?> responseEntity;
	try {
		theatres = theaterService.getByCity(city);
		responseEntity = new ResponseEntity<List<Theater>>(theatres, HttpStatus.OK);
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


	
	//	performing get theater by name
	@GetMapping("name/{name}")



	   public ResponseEntity<Theater> GETBY(@PathVariable("name") String name) {
	        
	        ResponseEntity<Theater> responseEntity;
	        try{
	            responseEntity = new ResponseEntity<Theater>(theaterService.validateAndFind(name), HttpStatus.OK);
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
	

	
	
	//	perfoming get operation to get the details of the theater using address field.
	@GetMapping("searchByAddress/{input}")
    public  ResponseEntity<List<Theater>>GETTHEATERBYADDRESS(@PathVariable("input") String input)throws NoContentException {
//    fetching the list of theaters using the theater field
		ResponseEntity<List<Theater>> responseEntity;
		try{
			responseEntity = new ResponseEntity<List<Theater>>(theaterService.getByAddress(input), HttpStatus.OK);
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
