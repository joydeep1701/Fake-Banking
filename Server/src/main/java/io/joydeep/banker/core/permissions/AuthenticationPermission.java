package io.joydeep.banker.core.permissions;

abstract class AuthenticationPermission implements Permission {
    private String permissionName;
    private int permissionLevel;

    @Override
    public String permissionName() {
        return permissionName;
    }

    @Override
    public int permissionLevel() {
        return permissionLevel;
    }
}
