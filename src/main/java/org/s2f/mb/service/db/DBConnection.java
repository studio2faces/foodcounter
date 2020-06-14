package org.s2f.mb.service.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);
    private static Connection dbc;

    private DBConnection() {
    }

    public static synchronized Connection getInstance() {
        if (dbc == null) {
            String connectionString = "jdbc:mysql://localhost:3306/fridge_counter_db.txt?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbc = DriverManager.getConnection(connectionString, "root", "root");
            } catch (ClassNotFoundException | SQLException e) {
                log.error("Exception", e);
            }
        }
        return dbc;
    }
}