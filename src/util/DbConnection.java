package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//String url = "jdbc:mysql://localhost:3306/mvcart";

		String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName); // load the class that is coming from .jar file
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root", "");
		if (con == null) {
			System.out.println("Db not connected......");
		} else {
			System.out.println("Db Connected.....");
		}
		return con;
	}

}
