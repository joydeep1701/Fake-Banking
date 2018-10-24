package io.joydeep.banker.core.permissions;

public class RegisterNewUserPermission implements Permission{
    final String permissionName = "REGISTER_NEW_USER_PERMISSION";
    final int permissionLevel = 1;

    @Override
    public String permissionName() {
        return permissionName;
    }

    @Override
    public int permissionLevel() {
        return permissionLevel;
    }
}
