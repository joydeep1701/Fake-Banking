package io.joydeep.banker.core.permissions;

public class LoginWithCLIPermission implements Permission{
    final String permissionName = "LOGIN_WITH_CLI";
    final int permissionLevel = 2;

    @Override
    public String permissionName() {
        return permissionName;
    }

    @Override
    public int permissionLevel() {
        return permissionLevel;
    }
}
