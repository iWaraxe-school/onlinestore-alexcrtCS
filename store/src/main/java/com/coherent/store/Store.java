package com.coherent.store;

import com.coherent.domain.Category;
import com.coherent.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categories;

    public Store() {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void printAllStoreProducts() {
        for (Category category : categories) {
            System.out.println(category.toString());
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            products.addAll(category.getProducts());
        }
        return products;
    }
}
