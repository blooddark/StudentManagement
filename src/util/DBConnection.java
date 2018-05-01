package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn = null;
	private static final String CLASSNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/stuManagement";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			if (conn != null && !conn.isClosed())
				return conn;
			Class.forName(CLASSNAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
