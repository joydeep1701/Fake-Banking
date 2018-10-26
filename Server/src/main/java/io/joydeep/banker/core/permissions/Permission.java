package io.joydeep.banker.core.permissions;


import io.joydeep.banker.core.database.SQLiteConnection;
import io.joydeep.banker.core.execptions.PermissionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Permission {
    private String permissionName;
    private int permissionLevel;
    private int permissionID;
    private static Logger logger = LoggerFactory.getLogger(Permission.class);

    Permission(String permissionName, int permissionLevel) {
        this.permissionLevel = permissionLevel;
        this.permissionName = permissionName;
    }

    public Permission(String permissionName) throws PermissionNotFoundException {
        Connection connection = SQLiteConnection.getConnection();
        String sql = "SELECT id, permission_name, permission_level FROM permissions WHERE permission_name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, permissionName);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            this.permissionID = rs.getInt("id");
            this.permissionName = rs.getString("permission_name");
            this.permissionLevel = rs.getInt("permission_level");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getSQLState());
            throw new PermissionNotFoundException();
        }
    }
    Permission(int permissionID) throws PermissionNotFoundException {
        Connection connection = SQLiteConnection.getConnection();
        String sql = "SELECT id, permission_name, permission_level FROM permissions WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, permissionID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            this.permissionID = rs.getInt("id");
            this.permissionName = rs.getString("permission_name");
            this.permissionLevel = rs.getInt("permission_level");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.error(e.getSQLState());
            throw new PermissionNotFoundException();
        }
    }
    public String getPermissionName() {
        return permissionName;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public int getPermissionID() {
        return permissionID;
    }
}
