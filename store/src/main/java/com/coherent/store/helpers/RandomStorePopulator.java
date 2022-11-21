package com.coherent.store.helpers;

import com.coherent.domain.Category;
import com.coherent.domain.Product;
import com.coherent.store.Store;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public static Set<Category> createCategories() {
        Set<Category> categories = new HashSet<>();
        Reflections reflections = new Reflections("com.coherent.domain.category");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            try {
                categories.add(type.getConstructor().newInstance());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return categories;
    }

    public void addProductsToCategories() {
        RandomProductCreator productCreator = new RandomProductCreator();
        Set<Category> categories = createCategories();

        for (Category category : categories) {
            for (int i = 0; i < 5; i++) {
                Product newProduct = new Product.Builder()
                        .name(productCreator.getName(category.getName()))
                        .rate(productCreator.getRate())
                        .price(productCreator.getPrice())
                        .build();
                category.addProduct(newProduct);
            }
            store.addCategory(category);
        }
    }
}