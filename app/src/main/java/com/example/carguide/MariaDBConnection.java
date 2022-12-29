package com.example.carguide;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDBConnection {
    public static Connection getConnection(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://163.18.71.78:9906/insurance";
            String username ="admin";
            String password = "Skills39";
            return DriverManager.getConnection(url,username,password);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return  null;
        }
    }
}
