package com.coherent.consoleApp.interaction;

import com.coherent.store.Store;

public class Action {
    private String action;
    private Store store;

    public Action(String action, Store store) {
        this.action = action;
        this.store = store;
    }

    public String getAction() {
        return action;
    }

    public Store getStore() {
        return store;
    }
}
