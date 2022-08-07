package com.company.dao.resources;

import com.company.dao.App;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties() {
        try {
            Properties configuration = new Properties();
            configuration.load(App.class.getClassLoader().getResourceAsStream("application.properties"));
            logger.log(Level.DEBUG, "Properties - load Properties");
            return configuration;
        } catch (Exception e) {
            logger.log(Level.ERROR, "Properties not loaded");
            throw new RuntimeException("Properties not loaded");
        }
    }

    static Logger logger = LogManager.getLogger();
}

