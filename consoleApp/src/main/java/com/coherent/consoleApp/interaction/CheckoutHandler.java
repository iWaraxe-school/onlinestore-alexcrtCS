package com.coherent.consoleApp.interaction;

import com.coherent.store.http.Client;

public class CheckoutHandler extends ActionHandler {
    private String actualAction = "checkout";
    Client client = new Client();

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            client.orderProduct();
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
