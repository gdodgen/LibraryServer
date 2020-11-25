package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import util.JDBCUtility;

public class DatabaseUserDAO {
	public ArrayList<User> getAllUsers(){ //returns all users in the database
		
		ArrayList<User> allUsers = new ArrayList<>();
		
		String sqlQuery = "SELECT * FROM users ";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				
				String role = rs.getString(2);
				
				String name = rs.getString(3);
				
				String email = rs.getString(4);
				
				
				User user = new User(id, name, role , email);
				
				allUsers.add(user);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}
	public User newUser(String userName, String role, String email, String password) { // creates a new user in the database
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
		connection.setAutoCommit(false);
		
		String sqlQuery = "INSERT INTO public.users "
				+ "(id, role, \"userName\", email, password)"
				+ "VALUES "
				+ "(DEFAULT, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, role);
		pstmt.setString(2, userName);
		pstmt.setString(3, email);
		pstmt.setString(4, password);
		
		if (pstmt.executeUpdate() != 1) {
			throw new SQLException("Inserting user failed, no rows were affected");
		}
		
		connection.commit();
		
		
		
		return new User(getNewId(userName), userName, role, email); 
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String getNewId(String userName) { //returns id of most recent new user as a string. used to log them in the first time and tell them their id.
		String id = null;
		ArrayList<User> users = new ArrayList<>();
		users = getAllUsers();
		int lastPlace = users.size() - 1;
		id = String.valueOf(users.get(lastPlace).getId());
		return id;
	}
	public void updateEmail(String id, String newEmail) { // changes email of user
		
		String sqlQuery = "UPDATE public.users SET email= '" + newEmail + "' WHERE id=" + id + ";";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
					
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("changing email failed, no rows were affected");
			}	
			
			connection.commit();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteUser(String id) { // removes user from db 
		
		String sqlQuery = "DELETE FROM public.users WHERE id=" + id +";";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
					
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("deleting user failed, no rows were affected");
			}	
			
			connection.commit();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public User getUser(String id, String password) { // verifies password and returns current user
		
		String userName;
		String role;
		String email;
		String passwordCheck;
		
		String sqlQuery = "Select * FROM public.users WHERE id= " + id + ";" ;
		
				
		try (Connection connection = JDBCUtility.getConnection()) {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			rs.next();
			
			passwordCheck = rs.getString(5);
			
			if(!passwordCheck.equals(password)) {
				return null;
			}
			
			role = rs.getString(2);
			
			userName = rs.getString(3);
			
			email = rs.getString(4);
		
		return new User(id, userName, role, email); 
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
