package com.company.dao.util;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements Closeable {
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Chestor25";

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public void close() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
