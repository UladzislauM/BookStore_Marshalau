package com.company.dao.util;

import com.company.dao.resources.PropertiesLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceElephant implements Closeable {
    private Connection connection;
    private static final Logger log = LogManager.getLogger(DataSourceElephant.class);

    public Connection getConnection() {
        if (connection == null) {
            try {
                Properties conf = PropertiesLoader.loadProperties();
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(conf.getProperty("URL_E"),
                        conf.getProperty("USER_E"), conf.getProperty("PASSWORD_E"));
                log.info("Create connection in to ElephantSQL - {}", connection);
            } catch (ClassNotFoundException | SQLException e) {
                log.error("Connection ERROR (ElephantSQL) - {}", e);
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
                log.error("Connection ERROR (ElephantSQL) - {}", e);
                throw new RuntimeException(e);
            }
        }
    }
}
