package com.coherent.store.http;

import com.coherent.store.http.handlers.CartHandler;
import com.coherent.store.http.handlers.CategoryHandler;
import com.coherent.store.http.handlers.MainHandler;
import com.coherent.store.http.handlers.ProductHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static final int PORT = 8080;
    HttpServer httpServer = null;

    private static class ServerSingleton {
        private static final Server SERVER_INSTANCE = new Server();
    }

    public static Server getInstance() {
        return ServerSingleton.SERVER_INSTANCE;
    }

    public void startServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);

        HttpContext mainContext = httpServer.createContext("/", new MainHandler());
        mainContext.setAuthenticator(new Authenticator("/"));

        HttpContext categoriesContext = httpServer.createContext("/categories", new CategoryHandler());
        categoriesContext.setAuthenticator(new Authenticator("/categories"));
        // Optional Product Handling Feature
        HttpContext productsContext = httpServer.createContext("/products", new ProductHandler());
        productsContext.setAuthenticator(new Authenticator("/products"));

        HttpContext orderContext = httpServer.createContext("/order", new CartHandler());
        productsContext.setAuthenticator(new Authenticator("/order"));

        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server is running...");
    }
}