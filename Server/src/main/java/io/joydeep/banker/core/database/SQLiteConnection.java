package io.joydeep.banker.core.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private static Connection connection = null;
    private static final String url = "jdbc:sqlite:sample.db";
    private static Logger logger = LoggerFactory.getLogger(SQLiteConnection.class);

    private SQLiteConnection() {
        // Constructor hidden
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the class using current class loader
                Class.forName("org.sqlite.JDBC");
                // Get a JDBC connection
                connection = DriverManager.getConnection(url);
            } catch (ClassNotFoundException | SQLException e) {
                // Log the exception
                logger.error(e.getMessage());
            }
        }
        return connection;
    }
}
