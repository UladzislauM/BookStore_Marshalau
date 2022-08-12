package com.company.dao.resources;

import com.company.dao.util.DataSourceElephant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger log = LogManager.getLogger(DataSourceElephant.class);

    public static Properties loadProperties() {
            log.debug("Properties - load Properties");
            Properties configuration = new Properties();
        try (InputStream input = PropertiesLoader.class.getResourceAsStream("/application.properties")){
            configuration.load(input);
            return configuration;
        } catch (Exception e) {
            log.error("Properties not loaded");
            throw new RuntimeException("Properties not loaded");
        }
    }
}

