package com.coherent.consoleApp;

import com.coherent.consoleApp.interaction.*;
import com.coherent.store.Store;
import com.coherent.store.helpers.DBHelper;
import com.coherent.store.http.Server;

import java.io.IOException;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        DBHelper db = DBHelper.getInstance();
        Server server = Server.getInstance();
        Scanner scanner = new Scanner(System.in);
        db.accessStore();

        try {
            server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Store Interaction with only commands interacting with Server & DB
        while (true) {
            System.out.println("\nChoose an action:" +
                    "\n\t-> categories" +
                    "\n\t-> products" +
                    "\n\t-> checkout" +
                    "\n\t-> quit");
            System.out.print("Action -> ");
            Action action = new Action(scanner.next(), store);
            ActionHandler actionChain = new QuitHandler();
            actionChain.link(new GetCategoryHandler())
                    .link(new GetProductsHandler())
                    .link(new CheckoutHandler());
            actionChain.perform(action);
        }
    }
}
