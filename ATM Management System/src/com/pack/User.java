package com.pack;

import java.util.Objects;

public class User{
    private String name;
    private int pin;
    private double balance;

    public User(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return getPin() == user.getPin() && getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pin);
    }
}
