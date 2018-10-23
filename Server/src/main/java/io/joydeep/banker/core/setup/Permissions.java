package io.joydeep.banker.core.setup;

import io.joydeep.banker.core.database.SQLiteConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class Permissions {
    private static Logger logger = LoggerFactory.getLogger(Permissions.class);

    static void createPermissionsTable() {
        Connection connection = SQLiteConnection.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS permissions ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " permission_name TEXT,"
                + " permission_level INTEGER"
                + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Permissions Table Created");
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }

    }
}
