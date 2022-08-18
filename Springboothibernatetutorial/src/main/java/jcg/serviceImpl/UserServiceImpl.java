package jcg.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jcg.dao.UserDAO;
import jcg.model.FlightDetails;
import jcg.model.User;
import jcg.model.UserBookings;
import jcg.services.UserRepository;
import jcg.services.UserService;

@Service
public class UserServiceImpl implements UserService,UserRepository {

	@Autowired
	UserDAO userrepo;
	
	@Override
	public ResponseEntity<User> createUser(User newUser) {
		
				 userrepo.createUser(newUser);
				return new ResponseEntity<User>(newUser,null, HttpStatus.OK);
			
	}

	@Override
	public User updateUser(User updateUser)  {
		System.out.println(updateUser.getUserName());
		 userrepo.updateUser(updateUser);
			return updateUser;
	}

	@Override
	public String deleteUser(int Id)  {
		User uDetails = userrepo.getUser(Id);
		if (uDetails!=null) {
			userrepo.deleteUser(Id);
			return "User Deleted!!";
		} else {}
		return null;
			
	}

	@Override
	public List<UserBookings> getUserFlights(String ID) {
		// TODO Auto-generated method stub
		return userrepo.getUserFlights(ID);
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userrepo.getUser(id);
	}

	@Override
	public void bookFlight(UserBookings booking) {
		// TODO Auto-generated method stub
		 userrepo.bookFlight(booking);
	}

	@Override
	public ResponseEntity<String> signIn(User user) {
		// TODO Auto-generated method stub
		
		User us=userrepo.signIn(user);
		if(us!=null) {
			try {
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(us),HttpStatus.OK);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<FlightDetails> searchFlights(FlightDetails newUser) {
		// TODO Auto-generated method stub
		return userrepo.searchFlights(newUser);
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return userrepo.getUser(userName);
	}

	@Override
	public void deleteUserFlight(int id) {
		// TODO Auto-generated method stub
		userrepo.deleteUserFlight(id);
	}

	@Override
	public List<UserBookings> searchQuery(String pnr, String email, String fromPlace, String toPlace) {
		// TODO Auto-generated method stub
		return userrepo.searchQuery( pnr,  email,  fromPlace,  toPlace);
	}

	
	

	

}

