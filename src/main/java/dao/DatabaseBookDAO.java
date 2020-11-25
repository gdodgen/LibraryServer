package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Book;
//import model.User;
import util.JDBCUtility;

public class DatabaseBookDAO {
	public ArrayList<Book> getLibraryStock(){ //returns table of every book the library has, both checked in an out
		
		ArrayList<Book> libraryStock = new ArrayList<>();
		
		String sqlQuery = "SELECT * FROM books ";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
			
				String title = rs.getString(1);
				
				String ISBN = rs.getString(2);
				
				String author = rs.getString(3);
				
				String genre = rs.getString(4);
				
				Boolean checkedOut = rs.getBoolean(5);
				
				String outID = rs.getString(6);
				
				Book book = new Book(title, ISBN, author , genre, checkedOut, outID);
				
				libraryStock.add(book);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryStock;
	}
	
	public ArrayList<Book> search(String criteria, String keyword, boolean checkStatus){  //passes in a keyword, bool checkedOUt, and search category. returns arraylist of books that match
		ArrayList<Book> searchResult = new ArrayList<>();
		
		String sqlQuery = "SELECT * FROM books WHERE " + criteria + "= '" + keyword +"';";
		if(checkStatus) {
			sqlQuery = "SELECT * FROM books WHERE " + criteria + "= '" + keyword +"' AND checkedout = false;";
		}
		try (Connection connection = JDBCUtility.getConnection()) {
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
			
				String title = rs.getString(1);
				
				String ISBN = rs.getString(2);
				
				String author = rs.getString(3);
				
				String genre = rs.getString(4);
				
				Boolean checkedOut = rs.getBoolean(5);
				
				String outID = rs.getString(6);
				
				Book book = new Book(title, ISBN, author , genre, checkedOut, outID);
				
				searchResult.add(book);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return searchResult;
	}
	
	
	public Book newBook(String title, String ISBN, String author, String genre) { //creates new book in the database
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			
			String sqlQuery = "INSERT INTO public.books "
					+ "(title, isbn, author, genre, checkedout)"
					+ "VALUES "
					+ "(?, ?, ?, ?, false)";
			
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, title);
			pstmt.setString(2, ISBN);
			pstmt.setString(3, author);
			pstmt.setString(4, genre);
			
			if (pstmt.executeUpdate() != 1) {
				throw new SQLException("Inserting book failed, no rows were affected");
			}
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new Book(title, ISBN, author, genre, false, null);
	}
	public void removeBook(String ISBN) { //deletes book from database 
		String sqlQuery = "DELETE FROM public.books WHERE isbn= '" + ISBN +"';";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
					
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("deleting book failed, no rows were affected");
			}	
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*public void checkOut(String ISBN) { //updates checkedOut to true for the book passed in, puts user's id in outID field
		
		String sqlQuery = "UPDATE public.books " + "SET checkedout= true, outid= " + null 
				+" WHERE isbn= '" + ISBN + "';";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			
			Statement stmt = connection.createStatement();
			
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("checkout failed, no rows were affected");
			}	
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	public void checkOut(String ISBN, String outID) { //updates checkedOut to false, puts outID back to null
		String sqlQuery = "UPDATE public.books " + "SET checkedout= TRUE, outid= " + outID 
				+" WHERE isbn= '" + ISBN + "';";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			
			Statement stmt = connection.createStatement();
			
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("checkout failed, no rows were affected");
			}	
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void checkIn(String ISBN) { //updates checkedOut to false, puts outID back to null
		String sqlQuery = "UPDATE public.books " + "SET checkedout= false, outid= " + null 
				+" WHERE isbn= '" + ISBN + "';";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			
			connection.setAutoCommit(false);
			
			Statement stmt = connection.createStatement();
			
			if (stmt.executeUpdate(sqlQuery) != 1) {
				throw new SQLException("checkout failed, no rows were affected");
			}	
			
			connection.commit();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
}
