package Service;

import java.util.ArrayList;
import Template.UserTemplate;

import dao.DatabaseUserDAO;
import model.User;

public class UserService {
	private DatabaseUserDAO userDAO;
	
	public UserService() {
		this.userDAO = new DatabaseUserDAO();
	}
	public UserService(DatabaseUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public ArrayList<User> getAllUsers() {		
		return userDAO.getAllUsers();
	}
	public User addUser(UserTemplate userTemplate) {
		
		return userDAO.newUser(userTemplate.getUserName(), userTemplate.getRole(), userTemplate.getEmail(), userTemplate.getPassword());
	}
	public void removeUser(String id) {
		userDAO.deleteUser(id);
	}
	public void updateEmail(String id, String newEmail) {
		userDAO.updateEmail(id, newEmail);
	}
	public User getUser(String id, String password) { 
		return userDAO.getUser(id, password);
	}
}
