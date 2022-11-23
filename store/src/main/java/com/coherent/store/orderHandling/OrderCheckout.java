package com.coherent.store.orderHandling;

import com.coherent.store.Store;

import java.util.TimerTask;

public class OrderCheckout extends TimerTask {
    @Override
    public void run() {
        Store.getInstance().orderProducts.clear();
        System.out.print("\nThread " + Thread.currentThread().getId() + ": order checkout successful!\n");
    }
}
