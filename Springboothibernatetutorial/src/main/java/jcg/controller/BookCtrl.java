package jcg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jcg.model.Book;
import jcg.repository.BookDao;

// Controller class.
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/springhibernateapi")
public class BookCtrl {

	@Autowired
	private BookDao bookdao;

	// Create a new record in database.
	@PostMapping(value= "/create")
	public ResponseEntity<Book> create(@RequestBody Book book) {
		int id = bookdao.createBook(book);
		if(id != 0)
			return new ResponseEntity<Book>(HttpStatus.CREATED);

		return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Fetch all books from the database.
	@GetMapping(value= "/getall")
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookdao.findAll());
	}
	@RequestMapping(value= "/test")
	public ResponseEntity<List<Book>> test() {
		System.out.println("test");
		return ResponseEntity.ok(bookdao.findAll());
	}

	// Fetch particular book from the database.
	@GetMapping(value= "/get/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int bookid) {
		Book book = bookdao.findById(bookid);
		if(book == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
}