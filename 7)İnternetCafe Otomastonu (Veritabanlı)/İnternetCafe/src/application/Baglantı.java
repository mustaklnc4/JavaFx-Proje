package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baglantý {
static Connection connection=null;
  public static Connection Baglan() {
	  
	  try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost/internetcafe","root","");
		return connection;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}
}
