package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import Service.UserService;
import Template.UserTemplate;


@WebServlet("/servlet.Login")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		switch(action) {
		
		case "/login":
			HttpSession session = req.getSession();
			String id = req.getParameter("id");
			String password = req.getParameter("password");
				
			resp.getWriter().write("logging in \n");
		
			session.setAttribute("id", id);
			session.setAttribute("password", password);
		
			User currUser = userService.getUser(id, password);
		
			if(currUser == null) {
				resp.getWriter().write("login failed");
			}
			else {
				resp.getWriter().write("Login succesful, Welcome!");
			}
			break;
		
		case "/logout":
			HttpSession session2 = req.getSession(false);
			session2.invalidate();
			resp.getWriter().write("goodbye");
			break;
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String role = req.getParameter("role");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserTemplate newUser = new UserTemplate(userName, role, email, password);
		
		User currUser = userService.addUser(newUser);
		
		String id = String.valueOf(currUser.getId());
		
		HttpSession session = req.getSession();
		session.setAttribute("id", id);
		session.setAttribute("password", password);
		
		resp.getWriter().write("account created, your id is: " + currUser.getId() + ". remember it, you need it to log in again");
		
	}
	
}
