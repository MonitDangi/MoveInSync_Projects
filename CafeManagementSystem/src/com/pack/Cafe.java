package com.pack;

public class Cafe {
    private final String cafeName;
    private final int numberOfChefs;
    private final Menu menu;


    public Cafe(String name, int numberOfChefs) {
        this.cafeName = name;
        this.numberOfChefs = numberOfChefs;
        menu = new Menu();
    }


    @Override
    public String toString() {
        return "Welcome to "+ cafeName +"\nNumber of Chefs in cafe is "+numberOfChefs+".\n"+ menu;
    }
}
