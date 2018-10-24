package io.joydeep.banker.core.setup;

import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Permissions.createPermissionsTable();
    }
}
