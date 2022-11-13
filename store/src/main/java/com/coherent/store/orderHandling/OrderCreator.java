package com.coherent.store.orderHandling;

import com.coherent.domain.Product;
import com.coherent.store.Store;

public class OrderCreator implements Runnable {
    private Product product;
    private int timeout;

    public OrderCreator(Product product, int timeout) {
        this.product = product;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\nThread " + Thread.currentThread().getId() + ": adding product to shopping cart...");
        Store.getInstance().orderProducts.add(product);
        for (Product orderProduct : Store.getInstance().orderProducts) {
            System.out.print(orderProduct.toString());
        }
    }
}
