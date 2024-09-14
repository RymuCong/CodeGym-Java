package com.cg.casestudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/e-commerce-servlet";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connected to database!");
    }

    public Connection getConnection(){
        return connection;
    }
}
