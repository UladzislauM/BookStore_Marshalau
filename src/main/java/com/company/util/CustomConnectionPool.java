package com.company.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum CustomConnectionPool {
    INSTANCE;

    private final BlockingQueue<ProxyConnection> freeConnectionsProxy;
    private final Queue<ProxyConnection> givenAwayConnectionsProxy;

    private final static int DEFAULT_POOL_SIZE = 5;

    private final Logger log = LogManager.getLogger(CustomConnectionPool.class);

    CustomConnectionPool() {
        String urlKey;
        String userKey;
        String passwordKey;
        String driverClassNameKey;
        String typeOfConnection = PropertiesLoader.loadProperties().getProperty("db");
        switch (typeOfConnection) {
            case "local" -> {
                urlKey = "db.local.url";
                userKey = "db.local.user";
                passwordKey = "db.local.password";
                driverClassNameKey = "db.local.driver-Class-Name";
            }
            default -> {
                urlKey = "db.elephant.url";
                userKey = "db.elephant.user";
                passwordKey = "db.elephant.password";
                driverClassNameKey = "db.elephant.driver-Class-Name";
            }
        }
        String url = PropertiesLoader.loadProperties().getProperty(urlKey);
        String user = PropertiesLoader.loadProperties().getProperty(userKey);
        String password = PropertiesLoader.loadProperties().getProperty(passwordKey);
        String driverClassName = PropertiesLoader.loadProperties().getProperty(driverClassNameKey);
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            log.error("driver-Class-Name not found: {}", e.getMessage(), e);
        }
        freeConnectionsProxy = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnectionsProxy = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnectionsProxy.offer(createConnection(url, user, password));
                log.info(i + " connection connected");
            } catch (SQLException e) {
                log.error("Connection number {} error: {}", i, e.getMessage(), e);
                throw new RuntimeException(e);
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
        try {
            ProxyConnection  proxyConnection = freeConnectionsProxy.take();
            log.info("Create connection in to SQL - {}", proxyConnection);
            givenAwayConnectionsProxy.offer(proxyConnection);
            return proxyConnection;
        } catch (InterruptedException e) {
            log.error("Connection create err: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        if (ProxyConnection.class == proxyConnection.getClass()) {
            givenAwayConnectionsProxy.remove(proxyConnection);
            freeConnectionsProxy.offer(proxyConnection);
        } else {
            log.error("This method only works with Proxy Connection! Invalid Connection release.");
            throw new RuntimeException("This method only works with Proxy Connection! Invalid Connection release.");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnectionsProxy.take().reallyClose();
            } catch (InterruptedException e) {
                log.error("Connection close err: {}", e.getMessage(), e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Deregister Driver err: {}", e.getMessage(), e);
            }
        });
    }
}
