package com.ku.people;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private PGSimpleDataSource pgSimpleDataSource;

    public DataSource(PGSimpleDataSource pgSimpleDataSource) {
        this.pgSimpleDataSource = pgSimpleDataSource;
    }

    public Connection getConnection() throws SQLException {
        pgSimpleDataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        pgSimpleDataSource.setUser("postgres");
        pgSimpleDataSource.setPassword("postgres");
        return pgSimpleDataSource.getConnection();
    }
}