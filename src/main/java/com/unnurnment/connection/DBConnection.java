package com.unnurnment.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String URL = "jdbc:mysql://localhost:3306/chat";
    public final String USERNAME = "root";
    public final String PASSWORD = "123456789";

    public Connection open(){
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void close(Connection connection){
        if(connection == null) return;
        try{
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
