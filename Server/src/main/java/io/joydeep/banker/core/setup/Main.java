package io.joydeep.banker.core.setup;

import io.joydeep.banker.core.execptions.InsufficientPermissionException;
import io.joydeep.banker.core.execptions.UserExistsException;
import io.joydeep.banker.core.user.Employee;
import io.joydeep.banker.core.user.Root;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
//        Permissions.createPermissionsTable();
//        User.createUserTable();
//        User.createUserPermissionsTable();

//        Permissions.getPermissionIdByName("LOGIN_WITH_CLI");
        Root root = new Root();
        Employee emp = new Employee();
        emp.setUserName("joydeep");
        emp.setPasswordHash("password");
        try {
            emp.createUser(root);
        } catch (UserExistsException e) {
            logger.info("User exists");
            logger.error(e.getMessage());
        } catch (InsufficientPermissionException e) {
            logger.info("Permission Error");
            logger.error(e.getMessage());
        }
    }
}
