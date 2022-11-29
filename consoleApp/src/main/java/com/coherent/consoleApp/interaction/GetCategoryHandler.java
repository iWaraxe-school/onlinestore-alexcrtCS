package com.coherent.consoleApp.interaction;

import com.coherent.store.http.Client;

public class GetCategoryHandler extends ActionHandler{
    private String actualAction = "categories";
    Client client = new Client();

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            client.displayCategories();
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
