package com.coherent.store.http;


import com.coherent.domain.Product;
import com.coherent.store.Store;
import com.coherent.store.helpers.DBHelper;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Client {
    private static final Gson gson = new Gson();
    DBHelper db = DBHelper.getInstance();

    public void displayMain() {
        String response = given().auth()
                .basic("admin","password")
                .when()
                .get("/")
                .body()
                .asString();
        System.out.println(response);
    }

    public void displayCategories() {
        String response = given().auth()
                .basic("admin","password")
                .when()
                .get("/categories")
                .body()
                .asString();
        System.out.println(response);
    }

    public void displayProducts() {
        String response = given().auth()
                .basic("admin","password")
                .when()
                .get("/products")
                .body()
                .asString();
        System.out.println(response);
    }

    public void orderProduct() {
        if (!db.getDBProducts().isEmpty()) {
            Random random = new Random();
            List<Product> products = db.getDBProducts();
            Product randomProduct = products.get(random.nextInt(products.size()));
            String json = gson.toJson(randomProduct);
            Response response = given().auth().basic("admin","password")
                    .header("Content-type", "application/json")
                    .and()
                    .body(json)
                    .when()
                    .post("/order")
                    .then()
                    .extract().response();
            System.out.println(response.asString());
        }
    }
}
