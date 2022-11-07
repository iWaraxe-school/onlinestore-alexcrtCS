package com.coherent.consoleApp;

import com.coherent.store.Store;
import com.coherent.store.helpers.RandomStorePopulator;
import com.coherent.store.xmlsort.ComparatorClass;
import com.coherent.store.xmlsort.XMLparser;

import java.util.Map;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        RandomStorePopulator storePopulator = new RandomStorePopulator(store);
        storePopulator.addProductsToCategories();
        store.printAllStoreProducts();

        Scanner scanner = new Scanner(System.in);
        XMLparser xmlParser = new XMLparser();
        Map<String, String> map = xmlParser.getMap();
        ComparatorClass comparator = new ComparatorClass();

        while (true) {
            System.out.println("\nChoose an action:\n\t-> sort\n\t-> top\n\t-> quit");
            System.out.print("Action -> ");
            String action = scanner.next();
            if (action.equalsIgnoreCase("quit")) {
                break;
            } else if (action.equalsIgnoreCase("sort")) {
                System.out.println("Choose sorting criterion:");
                map.keySet().forEach((key) -> System.out.println("\t-> " + key));
                System.out.print("Sorting Criterion -> ");
                String sortingCriterion = scanner.next();
                try {
                    comparator.printSortedProducts(store, map, sortingCriterion);
                } catch (RuntimeException e) {
                    System.out.println("Warning: Invalid Sorting Criterion. Try again...");
                }
            } else if (action.equalsIgnoreCase("top")){
                comparator.printTop5Products(store);
            } else {
                System.out.println("Warning: Invalid Action. Try again...");
            }
        }
    }
}
