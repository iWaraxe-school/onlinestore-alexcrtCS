package com.coherent.consoleApp;

import com.coherent.store.Store;
import com.coherent.store.helpers.DBHelper;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        DBHelper db = new DBHelper(store);
        db.accessStore();
    }
}
