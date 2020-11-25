package servlets;

import Service.BookService;
import Service.UserService;
import model.User;
import Template.BookTemplate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/servlet.BookServlet")
public class BookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private BookService bookService = new BookService();
	private UserService userService = new UserService();
	
	public BookServlet() {
		super();
	}
	public BookServlet(ObjectMapper objectMapper, BookService bookService) {
		this.objectMapper = objectMapper;
		this.bookService = bookService;
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		String action = req.getParameter("action");
		boolean checkStatus = Boolean.parseBoolean(req.getParameter("checkStatus"));
		
		String keyword = null;
		String jsonString = null;
		
		if(action != "/showAll") {
			keyword = req.getParameter("keyword");
		}
		
		switch(action) {
		
		case "/showAll":
			jsonString = objectMapper.writeValueAsString(bookService.getLibraryStock());
			break;
		
		case "/searchGenre":
			jsonString = objectMapper.writeValueAsString(bookService.search("genre", keyword, checkStatus)); 
			break;
			
		case "/searchAuthor":
			jsonString = objectMapper.writeValueAsString(bookService.search("author", keyword, checkStatus)); 
			break;
			
		case "/searchTitle":
			jsonString = objectMapper.writeValueAsString(bookService.search("title", keyword, checkStatus)); 
			break;
			
		case "/searchISBN":
			jsonString = objectMapper.writeValueAsString(bookService.search("isbn", keyword, checkStatus)); 
			break;
			
		}
		resp.getWriter().append(jsonString);
		
		resp.setContentType("application/json");
		resp.setStatus(200);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String ISBN = req.getParameter("ISBN");
		
		HttpSession session = req.getSession(false);  
	    String id = (String)session.getAttribute("id"); 
	    String password = (String)session.getAttribute("password"); 
	    User currUser = userService.getUser(id, password);
		
		switch(action) {
		case "/newBook":
			if (!currUser.getRole().equals("librarian")) {
				resp.getWriter().write("unauthorized");
				break;
			}
			String title = req.getParameter("title");
			String genre = req.getParameter("genre");
			String author = req.getParameter("author");
			BookTemplate bookTemplate = new BookTemplate(title, ISBN, author, genre, false);
			bookService.newBook(bookTemplate);
			break;
		
		case "/deleteBook":
			if (!currUser.getRole().equals("librarian")) {
				resp.getWriter().write("unauthorized");
				break;
			}
			bookService.removeBook(ISBN);
			break;
			
		case "/checkout":
			//resp.getWriter().write(" id: " + id);
			bookService.checkOut(ISBN, id);
			break;
			
		case "/checkin":
			bookService.checkIn(ISBN);
			break;
		}
			
	}
}
