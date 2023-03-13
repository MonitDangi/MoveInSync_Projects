package com.pack;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM moveInSyncATM = new ATM();
        int numberOfQuery = 1;
        while(numberOfQuery-- > 0){
            System.out.println("Enter your choice:\n1.Register as new user.\n2.Enter as existing user.");
            int ch = sc.nextInt();
            if(ch == 1){
                String userName;
                int userPin;
                System.out.println("Enter your name:");
                userName = sc.next();
                System.out.println("Enter a pin");
                userPin = sc.nextInt();
                User newUser = new User(userName, userPin, 0.0);
                if(moveInSyncATM.checkPin(userPin)){
                    System.out.println("Not able to register user.\nCreate a unique pin.");
                    numberOfQuery++;
                    continue;
                }
                else{
                    String result = moveInSyncATM.registerNewUser(newUser);
                    System.out.println(result);
                    System.out.println("Thanks for registering as new user.\nNow you can use your ATM.");
                    numberOfQuery++;
                }

            }
            else if(ch == 2){
                String userName;
                int userPin;
                System.out.println("Enter your name:");
                userName = sc.next();
                System.out.println("Enter a pin");
                userPin = sc.nextInt();
                User newUser = new User(userName, userPin, 0.0);
                if(!moveInSyncATM.checkUser(newUser)){
                    System.out.println("No such user found");
                    break;
                }
                while(true){
                    System.out.println("\n\nEnter your choice:");
                    System.out.println("1. Check Balance.\n2. Withdraw amount.\n3. Add amount.\n4.Check history.\n5. Logout.\n");
                    int choice = 0;
                    choice = sc.nextInt();
                    if(choice == 1){
                        String result = moveInSyncATM.checkBalance(newUser);
                        System.out.println("======================================================================================");
                        System.out.println(result);
                        System.out.println("======================================================================================");
                    }
                    else if(choice == 2){
                        System.out.println("Enter amount:");
                        int amount = sc.nextInt();
                        String result = moveInSyncATM.reduceAmount(amount, newUser);
                        System.out.println("======================================================================================");
                        System.out.println(result);
                        System.out.println("======================================================================================");
                    }
                    else if(choice == 3){
                        System.out.println("Enter amount:");
                        int amount = sc.nextInt();
                        String result = moveInSyncATM.addBalance(amount, newUser);
                        System.out.println("======================================================================================");
                        System.out.println(result);
                        System.out.println("======================================================================================");
                    }
                    else if(choice == 4){
                        ResultSet history = moveInSyncATM.checkHistory(newUser);
                        if(history == null){
                            System.out.println("======================================================================================");
                            System.out.println("No Transaction history");
                            System.out.println("======================================================================================");
                        }
                        else{
                            try{
                                System.out.println("======================================================================================");
                                while (history.next()) {
                                    System.out.println(history.getString("history")+" "+history.getInt("amount"));
                                    System.out.println("--------------------------------------------------------------------------------------");
                                }
                                System.out.println("======================================================================================");
                            }
                            catch (Exception exception){
                                System.out.println("Exception");
                            }
                        }
                    }
                    else if(choice == 5){
                        System.out.println("Thanks for visiting.");
                        break;
                    }
                    else{
                        System.out.println("Enter a valid option.");
                    }
                }
            }
            else{
                System.out.println("Please Enter a valid option:");
                numberOfQuery++;
            }
        }
    }
}
//
//
//
//
//    String userName;
//    int userPin;
//            System.out.println("Enter your name:");
//                    userName = sc.next();
//                    System.out.println("Enter a pin");
//                    userPin = sc.nextInt();
//                    User newUser = new User(userName, userPin, 0.0);
//                    while(true){
//                    System.out.println("Enter your choice:");
//                    System.out.println("1. Register as new User.\n2. Check Balance.\n3. Withdraw amount.\n4. Add amount.\n5.Check history.\n6. Logout.");
//                    int choice = 0;
//                    choice = sc.nextInt();
//                    if(choice == 1){
//                    if(moveInSyncATM.checkPin(userPin)){
//                    System.out.println("Not able to register user.\nCreate a unique pin.");
//                    break;
//                    }
//                    else{
//                    String result = moveInSyncATM.registerNewUser(newUser);
//                    System.out.println(result);
//
//                    }
//                    }
//                    else if(choice == 2){
//                    if(!moveInSyncATM.checkUser(newUser)){
//                    System.out.println("No such user found");
//                    break;
//                    }
//                    String result = moveInSyncATM.checkBalance(newUser);
//                    System.out.println(result);
//                    }
//                    else if(choice == 3){
//                    if(!moveInSyncATM.checkUser(newUser)){
//                    System.out.println("No such user found");
//                    break;
//                    }
//                    System.out.println("Enter amount:");
//                    int amount = sc.nextInt();
//                    String result = moveInSyncATM.reduceAmount(amount, newUser);
//                    System.out.println(result);
//                    }
//                    else if(choice == 4){
//                    if(!moveInSyncATM.checkUser(newUser)){
//                    System.out.println("No such user found");
//                    break;
//                    }
//                    System.out.println("Enter amount:");
//                    int amount = sc.nextInt();
//                    String result = moveInSyncATM.addBalance(amount, newUser);
//                    System.out.println(result);
//                    }
//                    else if(choice == 5){
//                    if(!moveInSyncATM.checkUser(newUser)){
//                    System.out.println("No such user found");
//                    break;
//                    }
//                    ResultSet history = moveInSyncATM.checkHistory(newUser);
//                    if(history == null){
//                    System.out.println("No Transaction history");
//                    }
//                    else{
//                    try{
//                    while (history.next()) {
//                    System.out.println(history.getString("history")+" "+history.getInt("amount"));
//                    }
//                    }
//                    catch (Exception exception){
//                    System.out.println("Exception");
//                    }
//                    }
//                    }
//                    else if(choice == 6){
//                    System.out.println("Thanks for visiting.");
//                    break;
//                    }
//                    else{
//                    System.out.println("Enter a valid option.");
//                    }
//                    }