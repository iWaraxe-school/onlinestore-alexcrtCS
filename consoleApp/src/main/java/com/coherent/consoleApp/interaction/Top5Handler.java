package com.coherent.consoleApp.interaction;

import com.coherent.store.xmlsort.ComparatorClass;

public class Top5Handler extends ActionHandler {
    private String actualAction = "top";
    private ComparatorClass comparator;

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            comparator = new ComparatorClass(expectedAction.getStore());
            comparator.getTop5Products();
        } else {
            System.out.println("Warning: Invalid Action. Try again...");
        }
    }
}
