package com.company.dao.resources;

import com.company.dao.App;

import java.io.IOException;
import java.util.Properties;

public class PropetiesLoader {
    public static Properties loadProperties() throws IOException {
        try {
            Properties configuration = new Properties();
            configuration.load(App.class.getClassLoader().getResourceAsStream("com/company/dao/resources/application.properties"));
            return configuration;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
