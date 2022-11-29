package com.theatre.controller;

// util imports
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;

import org.apache.logging.log4j.*;
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
import com.theatre.service.TheaterService;

// Controller --> ServiceImpl [Service]
@RestController
@RequestMapping("theater")
@CrossOrigin
public class TheaterController {

	@Autowired
	TheaterService theaterService;
	

//	Logger SetUp
	private static Logger theaterLogger = LogManager.getLogger(TheaterController.class.getName());

	
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
			theaterLogger.info("Successful Retrieval of Theater Details",responseEntity);			
		}
		catch (NoContentException e) {
			responseEntity = new ResponseEntity(e.getCode()+" : "+e.getMessage(),HttpStatus.BAD_REQUEST);
			theaterLogger.error(e.getCode()+" : "+e.getMessage(),responseEntity);
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			theaterLogger.error(e.getMessage(),responseEntity);
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
			theaterLogger.info("Theater Data has been successfully stored",responseEntity);
		}
		catch (UserAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>(e.getCode()+" : "+e.getMessage(), HttpStatus.OK);
			theaterLogger.error(e.getCode()+" : "+e.getMessage(),responseEntity);
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.METHOD_FAILURE);
			theaterLogger.error(e.getMessage(),responseEntity);
		}
		return responseEntity;
	}


	/*
	 * |--------API to update Theater Data into Database--------|
	 * Call : 
	 *  	POST : "http://localhost:9090/theater/update"
	*/
	
	@PutMapping("/update")
    public ResponseEntity<String> UPDATE(@RequestBody Theater theater) {
        
		/*
		 * 	Pseudo Code
		 * 		1. Create Response Entity Object
		 * 		2. Try saving theaters through Service Implementation
		 * 		3. if Successful send proper response with response code
		 * 		4. Catch and send proper Response Code																					
		 */
		
		ResponseEntity<String> responseEntity;
//        find the theater with the name given by the user
        Theater theaterdetails = theaterService.findByname(theater.getName());
        int id = theaterdetails.getId();
//           checking whether the theater id exists or not.
        if (!theaterService.isIdExists(id)) {
//        if id doesnot exists update operation cannot be performed.
            responseEntity = new ResponseEntity<String>("User does not exists", HttpStatus.CONFLICT);
        } else {
//            from database
            System.out.print("entering else part");
            Address address = theaterdetails.getAddress();

           // setting user object with database id
            theater.setId(theaterdetails.getId());
            Address add = theater.getAddress();
            List<Row> rowcontainer = theater.getRow();
            System.out.println(rowcontainer);
            int m=0;
            add.setId(address.getId());

           List<Row> row = theaterdetails.getRow();
            List<Row> rowone = new ArrayList<Row>();

           for (int i = 0; i < row.size(); i++) {
                System.out.println(row.size());
            System.out.println("entering row part");
//                 For Row
                Row rowi = row.get(i);
                System.out.println(rowi.getSeats());
                Row rowj = rowcontainer.get(i);
                System.out.println(rowj.getSeats());
                if (rowi.getSeats() != null) {
                    System.out.println("entering row part 2");
                    List<Seat> seati = new ArrayList<Seat>();
                    seati = rowi.getSeats();
                    
                    int totalseats = rowj.getTotalSeats();
                    List<Seat> listofseats = new ArrayList<Seat>();
                    for(int k=0;k<totalseats;k++) {
                        Seat seat = new Seat();
                        seat.setBooking(SeatBooking.NOTBOOKED);
                        listofseats.add(seat);            
                    }
//                    Store array of seats in a row
                    rowj.setSeats(listofseats);
                    rowcontainer.add(rowj);
                    List<Seat> seatj = new ArrayList<Seat>();
                    seatj = rowj.getSeats();
                    // storing that row in a arraylist                    
                    for (int j = 0; j < seati.size(); j++) {
                        System.out.println("entering Seat part1");
                        if (seati.get(j) != null && seati.get(j) != null) {
                            System.out.println("entering Seat part2");
                            Seat IndividualSeatj = seatj.get(j);                        
                            Seat IndividualSeati = seati.get(j);
                            IndividualSeatj.setId(IndividualSeati.getId());
                            System.out.print(IndividualSeatj);
                            System.out.print(IndividualSeati);
                       }
                        rowj.setSeats(seatj);
                        rowj.setId(rowi.getId());
                   }
                }
               rowone.add(rowj);
            }
            theater.setRow(rowone);
            theater.setId(theaterdetails.getId());
            String message = theaterService.updateTheater(theater);
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
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
	public ResponseEntity<List<Theater>> GETBYCITY(@PathVariable("city") String city) {
//	fetching the list of theaters belonging to that city.
		List<Theater> theatres = theaterService.getByCity(city);

		ResponseEntity<List<Theater>> responseEntity;
//checking whether the theater is empty or not.
		if (theatres.isEmpty()) {
//			if theater is empty returns null
			responseEntity = new ResponseEntity<List<Theater>>(theatres, HttpStatus.NO_CONTENT);
		} else {
//	if	 theater is not empty then returns the required list of theaters.
			responseEntity = new ResponseEntity<List<Theater>>(theatres, HttpStatus.OK);
		}
		return responseEntity;
	}


	
	//	performing get theater by name
	@GetMapping("name/{name}")

	public List<Theater> GETBY(@PathVariable("name") String name) {
//		creating a list to add the theater into theater details.
		List<Theater> theaterdetail = new ArrayList<Theater>(); 
		ResponseEntity<List<Theater>> responseEntity;
//		fetching the theater details using theater name.
		
		 theaterdetail.add(theaterService.findByname(name));
//		 checking whether the theater is empty or not.
		 if(theaterdetail.isEmpty()) {
//				if theater is empty returns null
			 responseEntity= new ResponseEntity<List<Theater>>(theaterdetail,HttpStatus.NO_CONTENT);
		 }
		 else {
//				if theater is not empty then returns the requiered list of theaters.
			 responseEntity= new ResponseEntity<List<Theater>>(theaterdetail,HttpStatus.OK);
		 }
		 return theaterdetail;
	}
	

	
	
	//	perfoming get operation to get the details of the theater using address field.
	@GetMapping("searchByAddress/{input}")
    public  ResponseEntity<List<Theater>>GETTHEATERBYADDRESS(@PathVariable("input") String input) {
//    fetching the list of theaters using the theater field
	List<Theater> theaters = theaterService.getByAddress(input);
    ResponseEntity<List<Theater>> responseEntity;
//    checking whether the theater is empty or not.
    if(theaters.isEmpty()) {
//    	if theater is empty returns null
            responseEntity = new ResponseEntity<List<Theater>>(theaters, HttpStatus.NO_CONTENT);
        }
        else
        {
//        	if theater is not empty then returns the requiered list of theatres.
            responseEntity = new ResponseEntity<List<Theater>>(theaters, HttpStatus.OK);
        }
        return responseEntity;
        }
}
