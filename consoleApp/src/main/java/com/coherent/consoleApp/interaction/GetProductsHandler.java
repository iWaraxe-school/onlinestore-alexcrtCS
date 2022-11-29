package com.coherent.consoleApp.interaction;

import com.coherent.store.http.Client;

public class GetProductsHandler extends ActionHandler{
    private String actualAction = "products";
    Client client = new Client();

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            client.displayProducts();
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
