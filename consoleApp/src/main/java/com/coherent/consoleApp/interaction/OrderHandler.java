package com.coherent.consoleApp.interaction;

import com.coherent.domain.Product;
import com.coherent.store.orderHandling.OrderCheckout;
import com.coherent.store.orderHandling.OrderCreator;

import java.util.Random;

public class OrderHandler extends ActionHandler {
    private String actualAction = "order";

    @Override
    public void perform(Action expectedAction) {
        if (actualAction.equalsIgnoreCase(expectedAction.getAction())) {
            int numberOfProducts = expectedAction.getStore().getProducts().size();
            Random random = new Random();
            OrderCheckout orderCheckout = new OrderCheckout();
            new Thread(orderCheckout).start();
            while (true) {
                Product product = expectedAction.getStore().getProducts().get(random.nextInt(numberOfProducts));
                OrderCreator orderCreator = new OrderCreator(product, random.nextInt(30) + 1);
                new Thread(orderCreator).start();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        } else {
            nextAction.perform(expectedAction);
        }
    }
}
