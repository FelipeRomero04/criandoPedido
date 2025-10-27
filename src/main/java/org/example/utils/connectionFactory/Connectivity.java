package org.example.utils.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connectivity {
    private final static String URL = "jdbc:postgresql://localhost:5432/dbexercise002";
    private final static String USER = "postgres";
    private final static String PASS = "593711";


    public static Connection connectionDb(){
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (SQLException e){
            throw new RuntimeException("Falha ao conectar-se com o banco de dados.");
        }

    }
}
