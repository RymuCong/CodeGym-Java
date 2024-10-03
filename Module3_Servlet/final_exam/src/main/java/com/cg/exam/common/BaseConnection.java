package com.cg.exam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection {

    private final Connection connection;// tạo một connection duy nhất kết nối với database
    private static final String URL = "jdbc:mysql://localhost:3306/TComplexDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public BaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Connected to database!");
    }
    public Connection getConnection(){
        return connection;
    }
}
