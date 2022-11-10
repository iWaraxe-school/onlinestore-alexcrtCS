package com.coherent.domain;

public class Product {
    private final String name;
    private final double rate;
    private final double price;

    public Product(Builder builder) {
        this.name = builder.name;
        this.rate = builder.rate;
        this.price = builder.price;
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

    public static class Builder {
        private String name;
        private double rate;
        private double price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder rate(double rate) {
            this.rate = rate;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
