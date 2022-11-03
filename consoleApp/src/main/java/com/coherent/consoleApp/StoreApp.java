package com.coherent.consoleApp;

import com.coherent.store.Store;
import com.coherent.store.helpers.RandomStorePopulator;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        RandomStorePopulator storePopulator = new RandomStorePopulator(store);
        storePopulator.addProductsToCategories();
        store.printAllStoreProducts();
    }
}
