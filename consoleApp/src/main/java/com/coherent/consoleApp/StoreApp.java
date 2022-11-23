package com.coherent.consoleApp;

import com.coherent.consoleApp.interaction.*;
import com.coherent.store.Store;
import com.coherent.store.helpers.RandomStorePopulator;
import com.coherent.store.http.Server;

import java.io.IOException;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        RandomStorePopulator storePopulator = new RandomStorePopulator(store);
        Scanner scanner = new Scanner(System.in);
        storePopulator.addProductsToCategories();
        store.printAllStoreProducts();

        Server server = Server.getInstance();
        try {
            server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Re-instated & Updated Store Interaction from Previous Tasks
        while (true) {
            System.out.println("\nChoose an action:" +
                    "\n\t-> sort" +
                    "\n\t-> top" +
                    "\n\t-> order" +
                    "\n\t-> categories" +
                    "\n\t-> products" +
                    "\n\t-> checkout" +
                    "\n\t-> quit");
            System.out.print("Action -> ");
            Action action = new Action(scanner.next(), store);
            ActionHandler actionChain = new QuitHandler();
            actionChain.link(new GetCategoryHandler())
                    .link(new GetProductsHandler())
                    .link(new CheckoutHandler())
                    .link(new SortingHandler())
                    .link(new OrderHandler())
                    .link(new Top5Handler());
            actionChain.perform(action);
        }
    }
}
