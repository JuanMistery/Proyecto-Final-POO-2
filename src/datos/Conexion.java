


package datos;

import java.sql.*;

public class Conexion {
    public static Connection realizarConexion() throws ClassNotFoundException, SQLException {
        String url, user, password;
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/driveplus";
        user = "root";       
        password = "197283";
        return DriverManager.getConnection(url, user, password);
    }
}

    

