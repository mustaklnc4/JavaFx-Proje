package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baglanti {
    static Connection connection=null;
    public static Connection Baglan() {

        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost/yetki","root","");
            return connection;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
