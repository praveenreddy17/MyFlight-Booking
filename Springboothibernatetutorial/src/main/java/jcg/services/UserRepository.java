package jcg.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jcg.model.User;
import jcg.model.UserBookings;



@Service
public interface UserRepository{

	 public List<UserBookings> getUserFlights(String iD);
	 public User getUser(int id);
	public void bookFlight(UserBookings booking);
	public List<UserBookings> searchQuery(String pnr, String email, String fromPlace, String toPlace);

	
}