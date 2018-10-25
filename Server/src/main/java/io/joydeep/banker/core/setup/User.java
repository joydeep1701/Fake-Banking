package io.joydeep.banker.core.setup;

import io.joydeep.banker.core.database.SQLiteConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class User {
    private static Logger logger = LoggerFactory.getLogger(User.class);

    static void createUserTable() {
        Connection connection = SQLiteConnection.getConnection();

        try {
            String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "user_name TEXT UNIQUE NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "is_active BOOLEAN NOT NULL"
                    + ");";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    static void createUserPermissionsTable() {
        Connection connection = SQLiteConnection.getConnection();

        try {
            String sql = "CREATE TABLE IF NOT EXISTS user_permissions ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "user_id INTEGER REFERENCES users(id),"
                    + "permission_id INTEGER REFERENCES permissions(id)"
                    + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    static void createSuperUser() {





    }
}
