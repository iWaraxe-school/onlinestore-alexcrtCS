package com.coherent.store.xmlsort;

import com.coherent.domain.Category;
import com.coherent.domain.Product;
import com.coherent.store.Store;

import java.util.*;

public class ComparatorClass {
    public List<Product> getSortedProducts(Store store, Map<String, String> map, String key) {
        List<Product> products = store.getProducts();
        String value = map.get(key);
        try {
            if (value.equalsIgnoreCase("asc")) {
                if (key.equalsIgnoreCase("name")) {
                    Collections.sort(products, Comparator.comparing(Product::getName));
                } else if (key.equalsIgnoreCase("price")) {
                    Collections.sort(products, Comparator.comparing(Product::getPrice));
                } else if (key.equalsIgnoreCase("rate")) {
                    Collections.sort(products, Comparator.comparing(Product::getRate));
                }
            } else if (value.equalsIgnoreCase("desc")) {
                if (key.equalsIgnoreCase("name")) {
                    Collections.sort(products, Comparator.comparing(Product::getName).reversed());
                } else if (key.equalsIgnoreCase("price")) {
                    Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
                } else if (key.equalsIgnoreCase("rate")) {
                    Collections.sort(products, Comparator.comparing(Product::getRate).reversed());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getTop5Products(Store store) {
        Map<String, String> topMap = new HashMap<>();
        topMap.put("price", "desc");
        return getSortedProducts(store, topMap, "price").subList(0, 5);
    }

    private void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.print(product.toString());
        }
    }

    public void printSortedProducts(Store store, Map<String, String> map, String key) {
        List<Product> sortedProducts = getSortedProducts(store, map, key);
        printProducts(sortedProducts);
    }

    public void printTop5Products(Store store) {
        List<Product> top5Products = getTop5Products(store);
        printProducts(top5Products);
    }
}