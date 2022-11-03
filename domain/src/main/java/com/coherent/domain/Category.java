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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nCATEGORY: ").append(name);
        for (Product product : products) {
            builder.append(product.toString());
        }
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
