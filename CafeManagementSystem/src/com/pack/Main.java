package com.pack;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int numberOfChefs;
        List<Chef> chefList = new ArrayList<>();
        final String waiterName;
        System.out.println("Enter number of chef in MoveInSync Cafe:\t");
        numberOfChefs = sc.nextInt();
        for(int i = 0; i < numberOfChefs; i++){
            System.out.println("Enter "+ (i+1)+ " Chef name:");
            String chefName = sc.next();
            Chef c = new Chef(chefName,0);
            chefList.add(c);
        }
        System.out.println("Enter waiter name:\t");
        waiterName = sc.next();
        Cafe moveInSyncCafe = new Cafe("MoveInSync Cafe",numberOfChefs);
        PriorityQueue<Chef> freeChef = new PriorityQueue<>(chefList);

        Menu menu = new Menu();
        int numberOfOrder = 5;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String lastOrderTime = LocalTime.now().format(formatter);
        while(numberOfOrder-- > 0){
            List<Integer> order = Order.startOrder(menu, waiterName);
            LocalTime orderTime = LocalTime.now();
            String newOrderTime = formatter.format(orderTime);
            int timePassed = TimeManager.calculateDifference(lastOrderTime, newOrderTime);
            lastOrderTime = newOrderTime;
            TimeManager.updateChefBusyTime(chefList, timePassed);
            int maxBusyTime = 0;
            for(int i = 3; i >=0; i--){
                int preparationTime;
                if(i == 3)preparationTime = menu.getItem4cookingTime();
                else if(i == 2)preparationTime = menu.getItem3cookingTime();
                else if(i == 1)preparationTime = menu.getItem2cookingTime();
                else preparationTime = menu.getItem1cookingTime();
                int quantity = order.get(i);
                while(quantity-- > 0){
                    Chef chef = freeChef.poll();
                    assert chef!=null;
                    int busyTime = chef.getCurrentBusyTime();
                    busyTime += preparationTime;
                    chef.setCurrentBusyTime(busyTime);
                    maxBusyTime = Math.max(maxBusyTime , busyTime);
                    freeChef.add(chef);
                }
            }
            String deliveryTime = TimeManager.addTime(newOrderTime, maxBusyTime);
            if(!newOrderTime.equals(deliveryTime)){
                System.out.println("Your order is placed at " + newOrderTime);
                System.out.println("Your order will be be delivered at "+ deliveryTime);
            }
            for(Chef x: chefList){
                System.out.println(x.getChefName() + " "+x.getCurrentBusyTime());
            }
        }
    }

}
