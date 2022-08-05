package com.company.dao.util;

import com.company.dao.resources.PropetiesLoader;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource implements Closeable {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Properties conf = PropetiesLoader.loadProperties();
                connection = DriverManager.getConnection(conf.getProperty("URL"),
                        conf.getProperty("USER"), conf.getProperty("PASSWORD"));
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
