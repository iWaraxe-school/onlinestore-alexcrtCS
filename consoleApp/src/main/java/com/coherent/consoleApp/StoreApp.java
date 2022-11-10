package com.coherent.consoleApp;

import com.coherent.store.Store;
import com.coherent.store.helpers.RandomStorePopulator;
import com.coherent.store.xmlsort.ComparatorClass;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        RandomStorePopulator storePopulator = new RandomStorePopulator(store);
        Scanner scanner = new Scanner(System.in);
        ComparatorClass comparator = new ComparatorClass(store);
        storePopulator.addProductsToCategories();
        store.printAllStoreProducts();

        while (true) {
            System.out.println("\nChoose an action:\n\t-> sort\n\t-> top\n\t-> quit");
            System.out.print("Action -> ");
            String action = scanner.next();
            if (action.equalsIgnoreCase("quit")) {
                break;
            } else if (action.equalsIgnoreCase("sort")) {
                comparator.getSortedProducts();
            } else if (action.equalsIgnoreCase("top")){
                comparator.getTop5Products();
            } else {
                System.out.println("Warning: Invalid Action. Try again...");
            }
        }
    }
}
