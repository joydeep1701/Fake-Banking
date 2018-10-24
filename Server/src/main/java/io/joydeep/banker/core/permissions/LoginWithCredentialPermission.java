package io.joydeep.banker.core.permissions;

public class LoginWithCredentialPermission implements Permission {
    final String permissionName = "LOGIN_WITH_CREDENTIALS";
    final int permissionLevel = 0;

    @Override
    public String permissionName() {
        return permissionName;
    }

    @Override
    public int permissionLevel() {
        return permissionLevel;
    }
}