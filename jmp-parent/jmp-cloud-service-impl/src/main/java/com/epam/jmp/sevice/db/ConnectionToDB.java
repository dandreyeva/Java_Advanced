package com.epam.jmp.sevice.db;

import java.sql.*;

public class ConnectionToDB {
    public static Connection getConnection() {
        Connection connection;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jmpdatabase", "jmpuser", "jmppass");
        } catch(ClassNotFoundException | SQLException err){
            throw new RuntimeException(err);
        }
        return connection;
    }
}
