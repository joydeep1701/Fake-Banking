package io.joydeep.banker.core.user;

import io.joydeep.banker.core.permissions.Permission;

/**
 * Shouldn't be a real user in the database. The root user is used to create the first superuser.
 */
public class Root extends User {
    @Override
    public final boolean hasPermission(Permission permission) {
        return true;
    }
}
