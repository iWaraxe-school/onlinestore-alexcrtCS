package com.coherent.domain;

public class Product {
    private String name;
    private double rate;
    private double price;

    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "\n\tName: " + getName() + ", Rate: " + getRate() + ", Price: " + getPrice();
    }
}
