package io.joydeep.banker.core.permissions;

public class LoginWithTokenPermission implements Permission {
    final String permissionName = "LOGIN_WITH_TOKEN";
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
