package org.example;

import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class App {

    public static final String URL = "jdbc:mysql://localhost:3306/sakila?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    public static final String User = "root";
    public static final String PASSWORD = "1234";

    public static void main(String[] args) throws SQLException {


        try {

            Connection connection = DriverManager.getConnection(URL
                    , User, PASSWORD);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM actor");

            while (rs.next()) {
                System.out.println("the ....."+ rs.getString("first_name"));
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }





    }
}