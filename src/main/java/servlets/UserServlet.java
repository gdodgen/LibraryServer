package servlets;

import Service.UserService;
//import Template.UserTemplate;
import model.User;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/servlet.UserServlet")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserService();
	
	public UserServlet() {
		super();
	}
	
	public UserServlet(ObjectMapper objectMapper, UserService userService) {
		this.objectMapper = objectMapper;
		this.userService = userService;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		String action = req.getParameter("action");
		String email = null;
		
		HttpSession session = req.getSession(false);  
	    String id = (String)session.getAttribute("id"); 
	    String password = (String)session.getAttribute("password"); 
	    User currUser = userService.getUser(id, password);
		
		switch(action) {
		case "/newEmail":
			email = req.getParameter("email");
			userService.updateEmail(id, email);
			break;
		case "/delete":
			if (!currUser.getRole().equals("librarian")) {
				resp.getWriter().write("unauthorized");
				break;
			}
			String toDelete = req.getParameter("id");
			userService.removeUser(toDelete);
			break;
		case "/test":
			resp.getWriter().write("welcome " + currUser.getUserName());
		}
		
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		String jsonString = objectMapper.writeValueAsString( userService.getAllUsers() );
		
		resp.getWriter().write(jsonString);
		
		
		resp.setContentType("application/json");
		resp.setStatus(200);
		
		
	}
}
