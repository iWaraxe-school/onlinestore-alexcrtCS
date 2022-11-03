package com.coherent.store;

import com.coherent.domain.Category;

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
            category.printProductList();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }
}
