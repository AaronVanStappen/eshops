package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
    private static final String URL = "jdbc:mysql://localhost/e-shops?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "12345";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
