package com.coherent.store.http;


import com.coherent.domain.Product;
import com.coherent.store.Store;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Client {
    private static final Gson gson = new Gson();
    Store store = Store.getInstance();

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
        if (!(store.getCategories().isEmpty() && store.getProducts().isEmpty())) {
            Product randomProduct = store.getProducts().stream().findAny().get();
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
