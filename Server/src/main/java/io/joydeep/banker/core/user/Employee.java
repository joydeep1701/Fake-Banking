package io.joydeep.banker.core.user;

import io.joydeep.banker.core.execptions.InsufficientPermissionException;
import io.joydeep.banker.core.permissions.Permission;

public class Employee extends User {
    @Override
    public final boolean hasPermission(Permission permission) {
        return super.hasPermission(permission);
    }

    @Override
    public final void addPermission(User mod, Permission permission) throws InsufficientPermissionException {
        super.addPermission(mod, permission);
    }
}
