package com.coherent.consoleApp;

import com.coherent.domain.BikeCategory;
import com.coherent.domain.PhoneCategory;
import com.coherent.domain.Product;
import com.coherent.store.Store;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        // Testing console app with hardcoded values - will remove after Faker & Reflections implementation
        Product iPhone14 = new Product("iPhone 14", 4.7, 800);
        Product iPhone14Pro = new Product("iPhone 14 Pro", 5, 1000);
        PhoneCategory phones = new PhoneCategory();
        phones.addProduct(iPhone14);
        phones.addProduct(iPhone14Pro);

        Product mountainBike = new Product("Mountain Bike", 5,2000);
        Product bmxBike = new Product("BMX Bike", 3.5, 999);
        BikeCategory bikes = new BikeCategory();
        bikes.addProduct(mountainBike);
        bikes.addProduct(bmxBike);

        store.addCategory(phones);
        store.addCategory(bikes);
        store.printAllStoreProducts();
    }
}
