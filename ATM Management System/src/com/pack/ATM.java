package com.pack;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;

public class ATM {
    public boolean checkPin(int pin){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = "userpin";
        try{
            PreparedStatement pinFound = connection.prepareStatement("select * from userpin where pin = ?");
            pinFound.setInt(1,pin);
            ResultSet pins = pinFound.executeQuery();
            if(pins.next()){
                return true;
            }
            else{
                PreparedStatement setPin = connection.prepareStatement("insert into userpin values(?,?)");
                setPin.setInt(1,pin);
                pinFound.setString(2,"Set");
                pinFound.executeUpdate();
            }
        }
        catch(Exception exception){
        }
        return false;
    }
    public boolean checkUser(User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tableFound = metaData.getTables(null,null,tableName,null);
            if(tableFound.next()){
                String query = "select balance from userbalance where userid = "+"\'"+tableName+"\'";
                PreparedStatement getBalance = connection.prepareStatement(query);
                ResultSet gotBalance = getBalance.executeQuery();
                while(gotBalance.next()){
                    user.setBalance(Double.parseDouble(gotBalance.getString(1)));
                }
                return true;
            }
        }
        catch(Exception exception){
        }
        return false;
    }
    public String registerNewUser(User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        try{
            if(checkPin(user.getPin())){
                return "User already Exist";
            }
            else {
                PreparedStatement createTable = connection.prepareStatement("create table " + tableName + "(history varchar(100), amount int)");
                PreparedStatement setBalance = connection.prepareStatement("insert into userbalance values(\'" + tableName + "\',0)");
                setBalance.executeUpdate();
                createTable.executeUpdate();
                setBalance.close();
                createTable.close();
                connection.close();
            }
        }
        catch (Exception exception){
            return "Error 203\nPlease try again.";
        }
        return "User registered successfully";
    }
    public String reduceAmount(double amount, User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        try{
            if(user.getBalance() >amount){
                user.setBalance(user.getBalance() - amount);
                String stringAmount = Double.toString(amount);
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String time = localDateTime.format(dateTimeFormatter);
                String addHistory = "insert into "+ tableName +" (history,amount) values (\'"+ time+" Debited\',"+stringAmount+")";
                PreparedStatement amountHistory = connection.prepareStatement(addHistory);
                amountHistory.executeUpdate();
                String updateQuery = "update userbalance set balance = "+ user.getBalance()+" where userid = \'"+tableName+"\'";
                PreparedStatement updateBalance = connection.prepareStatement(updateQuery);
                updateBalance.executeUpdate();
                amountHistory.close();
                updateBalance.close();
                connection.close();
                return "Amount has been debited Successfully";
            }
            else{
                connection.close();
                return "Not enough Amount";
            }
        }
        catch(Exception exception){
            return "Error 202\nNot able to search for tables";
        }
    }
    public String addBalance(double amount, User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        try{
            user.setBalance(user.getBalance() + amount);
            String stringAmount = Integer.toString((int)amount);
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String time = localDateTime.format(dateTimeFormatter);
            String addHistory = "insert into "+ tableName +" (history,amount) values (\'"+ time+" Credited\',"+stringAmount+")";
            PreparedStatement amountHistory = connection.prepareStatement(addHistory);
            amountHistory.executeUpdate();
            String updateQuery = "update userbalance set balance = "+ user.getBalance()+" where userid = \'"+tableName+"\'";
            PreparedStatement updateBalance = connection.prepareStatement(updateQuery);
            updateBalance.executeUpdate();
            amountHistory.close();
            updateBalance.close();
            connection.close();
            return "Amount has been credited Successfully";
        }
        catch(Exception exception){
            return "Error 201\nNot able to search for tables";
        }
    }
    public String checkBalance(User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        try{
            connection.close();
            return "Available balance is "+ user.getBalance();
        }
        catch(Exception exception){
            return "Error 204\nNot able to search for tables";
        }
    }
    public ResultSet checkHistory(User user){
        Connection connection = ConnectToDatabase.connectToDataBase();
        String tableName = user.getName()+ user.getPin();
        ResultSet history = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from "+tableName);
            history = preparedStatement.executeQuery();
            assert history !=null;
            return history;
        }
        catch(Exception exception){
        }
        return history;
    }
}
