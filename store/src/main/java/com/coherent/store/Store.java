package com.coherent.store;

import com.coherent.domain.Category;
import com.coherent.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {
    private List<Category> categories;
    public List<Product> orderProducts = new CopyOnWriteArrayList<>();

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
