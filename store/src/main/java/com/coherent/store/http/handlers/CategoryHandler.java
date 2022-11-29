package com.coherent.store.http.handlers;

import com.coherent.domain.Category;
import com.coherent.store.helpers.DBHelper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CategoryHandler implements HttpHandler {
    private static final Gson gson = new Gson();
    private static final StringBuilder sb = new StringBuilder();
    DBHelper db = DBHelper.getInstance();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sb.append("Categories:");
        for (Category category : new ArrayList<>(db.getDBCategories())) {
            sb.append(" ").append(category.getName()).append(";");
        }
        byte[] response = gson.toJson(sb.toString()).getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
        exchange.close();
    }
}