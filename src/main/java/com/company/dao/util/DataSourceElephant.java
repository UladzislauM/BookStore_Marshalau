package com.company.dao.util;

import com.company.dao.service.serviceImpl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum DataSourceElephant {
    INSTANCE;

    private final BlockingQueue<ProxyConnection> freeConnectionsProxy;
    private final Queue<ProxyConnection> givenAwayConnectionsProxy;

    private final static int DEFAULT_POOL_SIZE = 5;

    private final Logger log = LogManager.getLogger(DataSourceElephant.class);

    DataSourceElephant() {
        String urlKey = null;
        String userKey = null;
        String passwordKey = null;
        String driverClassNameKey = null;
        String typeOfConnection = PropertiesLoader.loadProperties().getProperty("db");
        switch (typeOfConnection) {
            case "elephant":
                urlKey = "db.elephant.url";
                userKey = "db.elephant.user";
                passwordKey = "db.elephant.password";
                driverClassNameKey = "db.elephant.driver-Class-Name";
            case "local":
                urlKey = "db.local.url";
                userKey = "db.local.user";
                passwordKey = "db.local.password";
                driverClassNameKey = "db.local.driver-Class-Name";
            default:
                urlKey = "db.elephant.url";
                userKey = "db.elephant.user";
                passwordKey = "db.elephant.password";
                driverClassNameKey = "db.elephant.driver-Class-Name";
        }
        String url = PropertiesLoader.loadProperties().getProperty(urlKey);
        String user = PropertiesLoader.loadProperties().getProperty(userKey);
        String password = PropertiesLoader.loadProperties().getProperty(passwordKey);
        String driverClassName = PropertiesLoader.loadProperties().getProperty(driverClassNameKey);
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            log.error("driver-Class-Name not found: {}", e);
        }
        freeConnectionsProxy = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnectionsProxy = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnectionsProxy.offer(createConnection(url, user, password));
                log.info(i + " connection connected");
            } catch (SQLException e) {
                log.error("Connection number {} error: {}", i, e);
            }
        }
    }

    public ProxyConnection createConnection(String url, String user, String password) throws SQLException {
        try {
            return new ProxyConnection(DriverManager.getConnection(url, user, password));
        } catch (SQLException e) {
            log.error("Error read connection attributes: ", e);
            throw new SQLException();
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            log.info("Create connection in to SQL - {}", proxyConnection);
            proxyConnection = freeConnectionsProxy.take();
            givenAwayConnectionsProxy.offer(proxyConnection);

        } catch (InterruptedException e) {
            log.error("Connection create err: {}", e);
            return null;
        }
        return proxyConnection;
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        if (ProxyConnection.class != proxyConnection.getClass()) {
            givenAwayConnectionsProxy.remove(proxyConnection);
            freeConnectionsProxy.offer(proxyConnection);
        } else {
            log.error("This method only works with Proxy Connection! Invalid Connection return.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnectionsProxy.take().reallyClose();
            } catch (InterruptedException e) {
                log.error("Connection close err: {}", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Deregister Driver err: {}", e);
            }
        });
    }
}
