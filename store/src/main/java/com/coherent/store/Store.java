package com.coherent.store;

import com.coherent.domain.Category;
import com.coherent.domain.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private List<Category> categories;
    // Creating list of products for order -> stored in threadsafe arraylist
    public List<Product> orderProducts = Collections.synchronizedList(new ArrayList<>());

    private Store() {
        categories = new ArrayList<>();
    }

    private static class SingletonHelper {
        private static final Store INSTANCE = new Store();
    }

    public static Store getInstance() {
        return SingletonHelper.INSTANCE;
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
