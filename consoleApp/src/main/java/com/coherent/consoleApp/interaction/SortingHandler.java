package com.coherent.consoleApp.interaction;


import com.coherent.store.xmlsort.ComparatorClass;

public class SortingHandler extends ActionHandler {
    private String actualAction = "sort";
    private ComparatorClass comparator;

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            comparator = new ComparatorClass(expectedAction.getStore());
            comparator.getSortedProducts();
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
