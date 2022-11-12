package com.coherent.consoleApp.interaction;


public abstract class ActionHandler {
    protected ActionHandler nextAction;

    public ActionHandler link(ActionHandler nextAction) {
        this.nextAction = nextAction;
        return nextAction;
    }

    public abstract void perform(Action expectedAction);
}