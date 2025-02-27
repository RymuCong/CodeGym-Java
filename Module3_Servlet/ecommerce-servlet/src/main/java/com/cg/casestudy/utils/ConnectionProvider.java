package com.cg.casestudy.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

        public static Connection getConnection() {
            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-commerce-servlet", "root", "root");
            } catch (Exception e) {
                System.err.println("Connection Error: " + e);
            }
            return con;
        }
}
