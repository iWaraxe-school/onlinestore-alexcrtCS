package com.coherent.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void printProductList() {
        System.out.println("\nCATEGORY: " + name);
        for (Product product : products) {
            System.out.println(
                    "\tName: " + product.getName() +
                    ", Rate: " + product.getRate() +
                    ", Price: " + product.getPrice()
            );
        }
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
