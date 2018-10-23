package io.joydeep.banker.core.user;

import io.joydeep.banker.core.permissions.Permission;

import java.util.List;

public abstract class User {
    private String name;
    private String userId;
    private boolean active;
    List<Permission> permissions;
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
        return false;
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
}

