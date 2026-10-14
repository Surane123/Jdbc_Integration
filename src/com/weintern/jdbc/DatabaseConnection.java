package com.weintern.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Creates JDBC connections using environment based credentials. */
public final class DatabaseConnection {
    private DatabaseConnection() { }
    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/java_development?useSSL=false&serverTimezone=UTC");
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
