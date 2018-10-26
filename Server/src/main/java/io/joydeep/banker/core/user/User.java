package io.joydeep.banker.core.user;

import io.joydeep.banker.core.database.SQLiteConnection;
import io.joydeep.banker.core.execptions.InsufficientPermissionException;
import io.joydeep.banker.core.execptions.PermissionNotFoundException;
import io.joydeep.banker.core.execptions.UserExistsException;
import io.joydeep.banker.core.execptions.UserNotFoundException;
import io.joydeep.banker.core.permissions.Permission;
import io.joydeep.banker.core.permissions.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

abstract class User {
    private String name;
    private int userId;
    private String userName;
    private boolean active;
    private List<Permission> permissions;
    private String token;
    private String passwordHash;
    private static Logger logger = LoggerFactory.getLogger(User.class);
    private static Connection connection = SQLiteConnection.getConnection();

    public static User loginWithToken() {
        User user = null;
        return user;
    }

    public static User loginWithCredentials() {
        User user = null;
        return user;
    }

    public String generateToken() {
        return "";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean hasPermission(Permission permission) {
        // For every permission in the list
        for(Permission p: permissions) {
            // Do a string compare
            if (p.getPermissionName().equals(permission.getPermissionName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * In order to add a permission to a user, the moderating user should have the permission in the first place
     * @param mod Moderating user
     * @param permission Permission to be added
     * @throws InsufficientPermissionException the mod user doesn't have the proper permission
     */
    public void addPermission(User mod, Permission permission) throws InsufficientPermissionException {
        // Check the moderator for the permission
        if (!mod.hasPermission(permission)) {
            throw new InsufficientPermissionException();
        }
        // Check if permission is already there permission
        if (hasPermission(permission)) {
            return;
        }
        permissions.add(permission);
        // Update db

    }

    public void logout() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String rawPassword) {
        this.passwordHash = passwordHash(rawPassword);
    }

    public static String passwordHash(String password) {
        try {
            // Get an instance of the sha256 message digest algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Get the bytes
            byte[] messageDigest = md.digest(password.getBytes());
            // get the corresponding BigInt
            BigInteger no = new BigInteger(1, messageDigest);
            // return as string
            return no.toString();
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    public int getUserId() {
        return userId;
    }

    private int getUserId(String userName) {
        try {
            String sql = "SELECT id FROM users WHERE user_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                logger.error("No such user found: {}",userName);
                return -1;
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            logger.error("SQLException at getUserID {}", e.getSQLState());
        }
        return -1;
    }

    public void createUser(User mod) throws UserExistsException, InsufficientPermissionException {
        try {
            if (!mod.hasPermission(new Permission(Permissions.REGISTER_NEW_USER_PERMISSION))) {
                throw new InsufficientPermissionException();
            }

            if (getUserId(userName) > 0) {
                throw new UserExistsException();
            }

            String sql = "INSERT INTO users (user_name, password, is_active) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setBoolean(3, isActive());
            preparedStatement.execute();

            this.userId = getUserId(userName);
            logger.info("User created with userId: {} userName: {}",userId, userName);
        } catch (PermissionNotFoundException e) {
            throw new RuntimeException("Permission not found");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void updateUser(User mod) throws UserNotFoundException {

    }
}

