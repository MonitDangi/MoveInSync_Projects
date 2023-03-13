package com.pack;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDatabase {
    private ConnectToDatabase(){/*Never Used Constructor*/}
    public static Connection connectToDataBase(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atmmanagementsystem","postgres","12345@Monit");
        }
        catch(Exception exception){
            System.out.println("Error 101.\nNot able to connect to Database.");
        }
        return connection;
    }
}
