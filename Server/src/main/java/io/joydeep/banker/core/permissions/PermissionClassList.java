package io.joydeep.banker.core.permissions;

import java.util.ArrayList;
import java.util.List;

public class PermissionClassList {
    public static List<Permission> permissionsList = new ArrayList<>();
    static {
        permissionsList.add((Permission) new LoginWithCLIPermission());
        permissionsList.add((Permission) new LoginWithCredentialPermission());
        permissionsList.add((Permission) new LoginWithTokenPermission());
        permissionsList.add((Permission) new RegisterNewUserPermission());
    }
}
