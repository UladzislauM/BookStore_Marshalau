package com.company.dao.resources;

import com.company.dao.App;
import com.company.dao.util.DataSourceElephant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class PropertiesLoader {
    private static final Logger log = LogManager.getLogger(DataSourceElephant.class);

    public static Properties loadProperties() {
        try {
            Properties configuration = new Properties();
            configuration.load(App.class.getClassLoader().getResourceAsStream("application.properties"));
            log.debug("Properties - load Properties");
            return configuration;
        } catch (Exception e) {
            log.error("Properties not loaded");
            throw new RuntimeException("Properties not loaded");
        }
    }
}

