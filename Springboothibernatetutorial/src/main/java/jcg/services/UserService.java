package jcg.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jcg.model.FlightDetails;
import jcg.model.User;



public interface UserService {

	public ResponseEntity<User> createUser(User newUser);

	public User updateUser(User newUser) ;

	public String deleteUser(int Id) ;

	public User getUser(int id);

	public ResponseEntity<String> signIn(User user);

	public List<FlightDetails> searchFlights(FlightDetails newUser);

	public User getUser(String userName);

	public void deleteUserFlight(int id);

}