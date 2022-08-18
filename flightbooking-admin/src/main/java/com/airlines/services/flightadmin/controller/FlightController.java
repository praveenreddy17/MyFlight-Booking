package com.airlines.services.flightadmin.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import com.airlines.services.flightadmin.entity.FlightDetails;
import com.airlines.services.flightadmin.entity.UserDetails;
import com.airlines.services.flightadmin.serviceimpl.FlightServiceImpl;
@RestController
@RequestMapping("/flight")
@CrossOrigin(origins="http://localhost:4200")
public class FlightController {
	
	@Autowired
    private FlightServiceImpl service;
	@PostMapping("/addflight")
	public ResponseEntity<String> addflight(@RequestBody FlightDetails details ) {	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   details.setCreatedDate(dtf.format(now));
		   service.save(details);
		return new ResponseEntity<String>("true",HttpStatus.OK)	;	
		
	}
	@RequestMapping("/getAllFlights")
	public List<FlightDetails> getAllFlights() {
		return service.findAll();
	}		

	@RequestMapping("/deleteFlight/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable int id) {
		service.deleteById(id);
		return new ResponseEntity<String>("true",HttpStatus.OK)	;
	}
	@PutMapping("/updateFlight")
	public String updateFlight(@RequestBody FlightDetails details) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   details.setCreatedDate(dtf.format(now));
		   service.save(details);
		   return "Flight updated successfully" ;		
	}
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserDetails details ) {	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   details.setCreatedDate(dtf.format(now));
		   service.saveUser(details);
		return "Flight added successfully";		
		
	}
	
}
