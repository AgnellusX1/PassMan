package com.stacks.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/manager";
    private static Connection connection;
    public static Statement statement;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, "root", "");
        statement = connection.createStatement();
    }

    public static Statement getStatement() { return statement; }

    public static void close() throws SQLException {

//        String query = "SELECT * FROM users";
//        ResultSet rs = statement.executeQuery(query);
//        while(rs.next()){
//            System.out.println(rs.getString("username"));
//        }
//        rs.close();

        statement.close();
        connection.close();
    }
}
