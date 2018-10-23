package io.joydeep.banker.core.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Hello World");
//        Permissions.createPermissionsTable();
    }
}
