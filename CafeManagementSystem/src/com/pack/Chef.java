package com.pack;

public class Chef implements Comparable<Chef>{
    private final String chefName;
    private int currentBusyTime;
    public Chef(String chefName, int currentBusyTime){
        this.chefName = chefName;
        this.currentBusyTime = currentBusyTime;
    }
    public int getCurrentBusyTime(){
        return currentBusyTime;
    }

    public void setCurrentBusyTime(int currentBusyTime) {
        this.currentBusyTime = currentBusyTime;
    }
    public String getChefName(){
        return chefName;
    }

    @Override
    public String toString() {
        return chefName;
    }

    @Override
    public int compareTo(Chef chef) {
        return this.currentBusyTime - chef.getCurrentBusyTime();
    }


}
