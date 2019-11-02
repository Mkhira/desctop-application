package Database;
import com.jfoenix.controls.JFXAlert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Databases {
    static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/software", "root", "");
        return connection;
    }


   

}

