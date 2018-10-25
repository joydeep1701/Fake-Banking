package io.joydeep.banker.core.user;

import io.joydeep.banker.core.permissions.Permission;

public class Root extends User {
    @Override
    public final boolean hasPermission(Permission permission) {
        return true;
    }
}
