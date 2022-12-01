package io.github.sodhipinky.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/music_player";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pinky@123";

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }
}
