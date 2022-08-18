package jcg.controller;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jcg.model.FlightDetails;
import jcg.model.User;
import jcg.model.UserBookings;
import jcg.services.UserRepository;
import jcg.services.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/user")

public class FlightConsumerController {

	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private UserService userService;	
	Random rnd = new Random();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	Calendar cal = Calendar.getInstance();  
	String today=sdf.format(cal.getTime());
	
	@RequestMapping("/getUserFlights/{id}")
	public List<UserBookings> getUserFlights(@PathVariable("id") String ID){
		List<UserBookings> readAirLines = userRepository.getUserFlights(ID);
		for(UserBookings flightDto:readAirLines) {
			System.out.println(flightDto.getFlightID());
		}
		return readAirLines;
	}
	@RequestMapping("/bookFlight")
	public ResponseEntity<String> bookFlight(@RequestBody FlightDetails fDetails){		
						 
		    Random rnd = new Random();
		    Long number = (long) rnd.nextInt(99999999);
			UserBookings booking= new UserBookings();
			booking.setFlightID(fDetails.getFlightID());			
			User user=userService.getUser(fDetails.getUserName());
			booking.setUserID(user.getUserID());
			booking.setJourneyDate(fDetails.getDate());
			booking.setCreatedDate( today);
			booking.setPNR("PNR-"+number);
			booking.setCreatedBy(user.getUserName());
			booking.setFromPlace(fDetails.getFromPlace());
			booking.setToPlace(fDetails.getToPlace());
			booking.setUserName(user.getUserName());
			booking.setCost(String.valueOf(fDetails.getCost()* Integer.valueOf(fDetails.getPassengersCount())));
			booking.setIsActive("Y");
			booking.setPassengersCount(fDetails.getPassengersCount());			
			 userRepository.bookFlight(booking);
		
		
		
			 return new ResponseEntity<String>("true",HttpStatus.OK)	;
	}
	
	@RequestMapping("/searchQuery")
	public String searchQuery(@RequestBody String searchQ){		
		JSONObject obj= new JSONObject(searchQ);
		String pnr=null;
		String email=null;
		String fromPlace=null;
		String toPlace=null;
		if(obj.has("pnr")) {
			pnr=obj.getString("pnr");
		}
		if(obj.has("email")) {
			email=obj.getString("email");
		}
		if(obj.has("fromPlace")) {
			fromPlace=obj.getString("fromPlace");
		}
		if(obj.has("toPlace")) {
			toPlace=obj.getString("toPlace");
		}
		List<UserBookings> bookings=	 userRepository.searchQuery(pnr,email,fromPlace,toPlace);
		
		
		
		try {
			return new ObjectMapper().writeValueAsString(bookings);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
