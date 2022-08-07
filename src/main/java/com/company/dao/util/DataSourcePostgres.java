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

public class DataSourcePostgres implements Closeable {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Properties conf = PropertiesLoader.loadProperties();
                connection = DriverManager.getConnection(conf.getProperty("URL"),
                        conf.getProperty("USER"), conf.getProperty("PASSWORD"));
                logger.log(Level.INFO, "Create connection to PostgreSQL localhost");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection ERROR (localhost)");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection ERROR (localhost)");
                throw new RuntimeException(e);
            }
        }
    }

    static Logger logger = LogManager.getLogger();
}
