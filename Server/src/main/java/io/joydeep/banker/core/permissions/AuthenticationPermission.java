package io.joydeep.banker.core.permissions;

abstract class AuthenticationPermission implements Permission {
    String permissionName;
    int permissionLevel;
}
