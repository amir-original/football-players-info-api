package com.rest.apidemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDbConnection {

    public Connection getDbConnection() {
        String host = "jdbc:mysql://localhost:3306/football ";
        String user = "amir";
        String pass = "@wsrmp1378";
        try {
            Connection connection = DriverManager.getConnection(host, user, pass);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
