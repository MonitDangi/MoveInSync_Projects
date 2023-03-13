package com.pack;

public class Menu {
    private final String itemName1;
    private final String itemName2;
    private final String itemName3;
    private final String itemName4;
    private final int item1CookingTime;
    private final int item2CookingTime;
    private final int item3CookingTime;
    private final int item4CookingTime; p

    public Menu() {
        this.itemName1 = "Coffee";
        this.itemName2 = "Cereal";
        this.itemName3 = "Sandwich";
        this.itemName4 = "Pizza";
        item1CookingTime = 3;
        item2CookingTime = 3;
        item3CookingTime = 5;
        item4CookingTime = 7;
    }

    public String getItemName1() {
        return itemName1;
    }

    public String getItemName2() {
        return itemName2;
    }

    public String getItemName3() {
        return itemName3;
    }

    public String getItemName4() {
        return itemName4;
    }

    public int getItem1cookingTime() {
        return item1CookingTime;
    }

    public int getItem2cookingTime() {
        return item2CookingTime;
    }

    public int getItem3cookingTime() {
        return item3CookingTime;
    }

    public int getItem4cookingTime() {
        return item4CookingTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "1." + itemName1 + "\n2." + itemName2 + "\n3." + itemName3 + "\n4." + itemName4 ;
    }
}
