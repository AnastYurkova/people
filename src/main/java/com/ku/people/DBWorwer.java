package com.ku.people;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorwer {
    private Connection connection;

    public DBWorwer() throws SQLException {
        connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
    }

    public Connection getConnection() {
        return connection;
    }
}

