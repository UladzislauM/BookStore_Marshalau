package com.company.dao.util;

import com.company.dao.resources.PropertiesLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceElephant implements Closeable {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Properties conf = PropertiesLoader.loadProperties();
                connection = DriverManager.getConnection(conf.getProperty("URL_E"),
                        conf.getProperty("USER_E"), conf.getProperty("PASSWORD_E"));
                logger.log(Level.INFO, "Create connection to PostgreSQL host:ElephantSQL");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection  ERROR (host:ElephantSQL)");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    static Logger logger = LogManager.getLogger();

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection close ERROR (host:ElephantSQL)");
                throw new RuntimeException(e);
            }
        }
    }
}
