package com.pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private Order(){/* No Use*/}
    public static List<Integer> startOrder(Menu menu, String waiterName){
        List<Integer> newOrder = new ArrayList<>(4);
        Scanner sc = new Scanner(System.in);
        int item1 = 0;
        int item2 = 0;
        int item3 = 0;
        int item4 = 0;
        int choice;
        int quantity;
        System.out.println("\n\n\nHello Sir, Welcome to MoveInSync Cafe.\nMy name is "+waiterName+". I am here to take your order. What you would like to order");
        System.out.println("1. "+ menu.getItemName1()+"\n2. "+menu.getItemName2()+"\n3. "+menu.getItemName3()+"\n4. "+menu.getItemName4()+"\n5. Nothing else.");
        System.out.println("Enter your choice:");
        choice = sc.nextInt();
        if(choice == 5){
            newOrder.add(item1);newOrder.add(item2);newOrder.add(item3);newOrder.add(item4);
            return newOrder;
        }
        System.out.println("Enter quantity for the item");
        quantity = sc.nextInt();
        if(choice == 1)item1 += quantity;
        else if(choice == 2)item2 += quantity;
        else if(choice == 3)item3 += quantity;
        else if(choice == 4)item4 += quantity;
        else{
            System.out.println("We are not able to process your order.");
            return newOrder;
        }

        while(true){
            System.out.println("Would you like to add something else:");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            if(choice == 5)break;
            System.out.println("Enter quantity for the item");
            quantity = sc.nextInt();
            if(choice == 1)item1 += quantity;
            else if(choice == 2)item2 += quantity;
            else if(choice == 3)item3 += quantity;
            else if(choice == 4)item4 += quantity;
            else System.out.println("You chose wrong option. Please check your choice");
        }
        newOrder.add(item1);newOrder.add(item2);newOrder.add(item3);newOrder.add(item4);
        return newOrder;
    }
}
