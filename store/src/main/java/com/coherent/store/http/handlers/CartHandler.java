package com.coherent.store.http.handlers;

import com.coherent.domain.Product;
import com.coherent.store.orderHandling.OrderCheckout;
import com.coherent.store.orderHandling.OrderCreator;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CartHandler implements HttpHandler {
    private static final Gson gson = new Gson();
    private static String response;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();

        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String request = bufferedReader.readLine();
            Product product = gson.fromJson(request, Product.class);

            OrderCreator orderCreator = new OrderCreator(product, 0);
            new Thread(orderCreator).start();

            OrderCheckout orderCheckout = new OrderCheckout();
            new Thread(orderCheckout).start();

            response = "Product ordered successfully!";
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
        } else {
            response = "Method is not allowed! Try sending a POST request instead...";
            exchange.sendResponseHeaders(405, response.length());
            os.write(response.getBytes());
        }

        os.close();
        exchange.close();
    }
}