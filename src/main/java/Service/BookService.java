package Service;

import java.util.ArrayList;

import Template.BookTemplate;
import dao.DatabaseBookDAO;
import model.Book;
//import model.User;

public class BookService {
	private DatabaseBookDAO bookDAO;
	
	public BookService() {
		this.bookDAO = new DatabaseBookDAO();
	}
	
	public BookService(DatabaseBookDAO BookDAO) {
		this.bookDAO = BookDAO;
	}
	public ArrayList<Book> getLibraryStock() {
		
		return bookDAO.getLibraryStock();
	}
	/*public Book addBook(UserTemplate userTemplate) {
		
		//return bookDAO.newBook(bookTemplate.getUserName(), userTemplate.getRole());
	}*/
	public ArrayList<Book> search(String criteria, String keyword, boolean checkStatus){ 
		return bookDAO.search(criteria, keyword, checkStatus);
	}
	public Book newBook(BookTemplate bookTemplate) {
		return bookDAO.newBook(bookTemplate.getTitle(), bookTemplate.getISBN(), bookTemplate.getAuthor(), bookTemplate.getGenre());
	}
	public void removeBook(String ISBN) {
		bookDAO.removeBook(ISBN);
	}
	public void checkOut(String ISBN, String outID) {
		bookDAO.checkOut(ISBN, outID);
	}
	public void checkIn(String ISBN) {
		bookDAO.checkIn(ISBN);
	}
}