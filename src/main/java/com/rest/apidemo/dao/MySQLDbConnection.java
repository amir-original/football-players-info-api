package com.rest.apidemo.dao;

import com.rest.apidemo.helper.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDbConnection {

    private String host;
    private String user;
    private String pass;

    public MySQLDbConnection() {
        initDbConfig();
    }

    private void initDbConfig() {
        PropertiesReader config = new PropertiesReader("db-config");
        host = config.getProperty("host");
        user = config.getProperty("username");
        pass = config.getProperty("password");
    }

    public Connection getDbConnection() {
        try {
            Connection connection = DriverManager.getConnection(host, user, pass);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
