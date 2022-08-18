package jcg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jcg.model.FlightDetails;
import jcg.model.User;
import jcg.services.UserService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userService;
	Random rnd = new Random();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	Calendar cal = Calendar.getInstance();  
	String today=sdf.format(cal.getTime());
	@RequestMapping("/createUser")	
	public boolean addUser(@RequestBody User user) {		
		user.setCreatedDate(today);
		user.setIsActive("Y");
		user.setCreatedBy(user.getUserName());
		userService.createUser(user);
		return true;	
	}

	

	@RequestMapping("/updateUser")	
	public void updateUser(@RequestBody User newUser)  {		
		userService.updateUser(newUser);
	}
	@RequestMapping("/searchFlights")	
	public List<FlightDetails> searchFlights(@RequestBody FlightDetails newUser)  {		
		return userService.searchFlights(newUser);
	}
	
	@RequestMapping("/getUser/{id}")	
	public User getUser(@PathVariable("id") int Id)  {		
		return userService.getUser(Id);
	}

	
	@RequestMapping("/deleteUser/{id}")	
	public void deleteBookingByID(@PathVariable("id") int Id)  {
		userService.deleteUser(Id);
	}
	
	@RequestMapping("/deleteUserFlight/{id}")	
	public ResponseEntity<String> deleteUserFlight(@PathVariable("id") int Id)  {
		userService.deleteUserFlight(Id);
		 return new ResponseEntity<String>("true",HttpStatus.OK)	;
	}
	@RequestMapping("/login")	
	public ResponseEntity<String> signIn(@RequestBody User user)  {
		System.out.println("v"+user.getUserName());
		System.out.println("v"+user.getPassword());
		return userService.signIn(user);
	}
	@RequestMapping("/test")		
	public ResponseEntity<String> test()  {
		System.out.println("vtest");
		return null;
	}
	
}
 