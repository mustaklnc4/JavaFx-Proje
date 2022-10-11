package application;


import java.sql.Connection;
import java.sql.DriverManager;




public class sqlBaglanti {
	static Connection connection=null;
	
	public static Connection Baglan() {
		
		try {
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost/kargo","root","");
			
			return connection;
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
		
	}

}





