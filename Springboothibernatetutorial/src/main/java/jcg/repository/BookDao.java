package jcg.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jcg.model.Book;

// Database repository class.
@Repository
public class BookDao {

	@Autowired
	private SessionFactory sf;

	// Save book in the db.
	public Integer createBook(Book book) {
		Session s = sf.getCurrentSession();
		
		s.beginTransaction();
		Integer id = (Integer) s.save(book);
		s.getTransaction().commit();
		
		return id;
	}

	// Get all books.
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Book> findAll() {
		Session s = sf.getCurrentSession();
		List<Book> list = s.createCriteria(Book.class).list();
		return list;
	}

	// Find book by id.
	public Book findById(int bookid) {
		Session s = sf.getCurrentSession();
		Book book = s.get(Book.class, bookid);
		return book;
	}
}