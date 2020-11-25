package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;


public class JDBCUtility {
	public static Connection getConnection() throws SQLException {
		
		//String url = "jdbc:postgresql://localhost:5432/postgres";
		
		String url = "jdbc:postgresql://database-2.csgqotsju8z6.us-west-1.rds.amazonaws.com:5432/project0";
		
		String username = "postgres";
		String password = "testpassword";
		
		Connection connection = null;
		
		DriverManager.registerDriver(new Driver());
		
		connection = DriverManager.getConnection(url, username, password);
		
		return connection;
	}

}
	