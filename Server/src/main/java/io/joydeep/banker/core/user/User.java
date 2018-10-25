package io.joydeep.banker.core.user;

import io.joydeep.banker.core.execptions.InsufficientPermissionException;
import io.joydeep.banker.core.permissions.Permission;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

abstract class User {
    private String name;
    private String userId;
    private boolean active;
    private List<Permission> permissions;
    String token;

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
            if (p.permissionName().equals(permission.permissionName())) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
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
}

