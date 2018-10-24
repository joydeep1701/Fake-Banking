package io.joydeep.banker.core.setup;

import io.joydeep.banker.core.database.SQLiteConnection;
import io.joydeep.banker.core.permissions.Permission;
import io.joydeep.banker.core.permissions.PermissionClassList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class Permissions {
    private static Logger logger = LoggerFactory.getLogger(Permissions.class);

    /**
     * creates the permission table in the database along with the appropriate permission classes
     */
    static void createPermissionsTable() {
        Connection connection = SQLiteConnection.getConnection();

        try {
            /* Create the table if not exists */
            Statement statement = connection.createStatement();
            // Hard-coded
            String sql = "CREATE TABLE IF NOT EXISTS permissions ("
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " permission_name TEXT,"
                    + " permission_level INTEGER"
                    + ");";
            statement.execute(sql);
            logger.info("Permissions Table Created");

            /* Create the permissions */
            for (Permission p: PermissionClassList.permissionsList) {
                logger.info("CREATING PERMISSION {}", p.permissionName());

                sql = "INSERT INTO permissions(permission_name, permission_level) VALUES(?,?)";
                // Prepared statement allows to dynamically inject values into the sql query
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // SQL injection proof
                preparedStatement.setString(1, p.permissionName());
                preparedStatement.setInt(2, p.permissionLevel());
                preparedStatement.execute();
            }
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }

    }
}
