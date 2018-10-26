package io.joydeep.banker.core.permissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Permissions {
    private static Logger logger = LoggerFactory.getLogger(Permissions.class);

    public static List<Permission> permissionsList = new ArrayList<>();

    public static String LOGIN_WITH_CLI_PERMISSION = "LOGIN_WITH_CLI_PERMISSION";
    public static String LOGIN_WITH_CREDENTIAL_PERMISSION = "LOGIN_WITH_CREDENTIAL_PERMISSION";
    public static String LOGIN_WITH_TOKEN_PERMISSION = "LOGIN_WITH_TOKEN_PERMISSION";
    public static String REGISTER_NEW_USER_PERMISSION = "REGISTER_NEW_USER_PERMISSION";


    static {
        permissionsList.add(new Permission(LOGIN_WITH_CLI_PERMISSION, 2));
        permissionsList.add(new Permission(LOGIN_WITH_CREDENTIAL_PERMISSION, 0));
        permissionsList.add(new Permission(LOGIN_WITH_TOKEN_PERMISSION, 0));
        permissionsList.add(new Permission(REGISTER_NEW_USER_PERMISSION, 1));
    }
}
