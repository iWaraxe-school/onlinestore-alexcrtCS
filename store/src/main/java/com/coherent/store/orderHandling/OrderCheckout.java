package com.coherent.store.orderHandling;

import com.coherent.store.Store;

public class OrderCheckout implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\nThread " + Thread.currentThread().getId() + ": order checkout...");
            Store.getInstance().orderProducts.clear();
        }
    }
}
