package com.coherent.consoleApp.interaction;


public class QuitHandler extends ActionHandler {
    private String actualAction = "quit";
    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            System.exit(0);
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
