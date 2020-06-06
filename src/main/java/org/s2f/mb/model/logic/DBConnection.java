package org.s2f.mb.model.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection dbc;

    private DBConnection() {
    }

    public static synchronized Connection getInstance() {
        if (dbc == null) {
            String connectionString = "jdbc:mysql://localhost:3306/fridge_counter_db?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbc = DriverManager.getConnection(connectionString, "root", "root");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return dbc;
    }
}
