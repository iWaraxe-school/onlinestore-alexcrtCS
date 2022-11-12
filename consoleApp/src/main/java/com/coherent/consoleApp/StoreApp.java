package com.coherent.consoleApp;

import com.coherent.consoleApp.interaction.*;
import com.coherent.store.Store;
import com.coherent.store.helpers.RandomStorePopulator;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        RandomStorePopulator storePopulator = new RandomStorePopulator(store);
        Scanner scanner = new Scanner(System.in);
        storePopulator.addProductsToCategories();
        store.printAllStoreProducts();

        while (true) {
            System.out.println("\nChoose an action:\n\t-> sort\n\t-> top\n\t-> quit");
            System.out.print("Action -> ");
            Action action = new Action(scanner.next(), store);
            ActionHandler actionChain = new QuitHandler();
            actionChain.link(new SortingHandler()).link(new Top5Handler());
            actionChain.perform(action);
        }
    }
}
