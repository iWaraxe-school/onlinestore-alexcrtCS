package com.coherent.store.helpers;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomProductCreator {
    Faker faker = new Faker(new Locale("en-US"));

    public String getName(String categoryName) {
        return switch (categoryName) {
            case "Bikes" -> faker.commerce().productName();
            case "Phones" -> faker.company().name();
            case "Milk" -> faker.food().ingredient();
            default -> null;
        };
    }

    public double getRate() {
        return faker.number().randomDouble(1, 1, 10);
    }

    public double getPrice() {
        try {
            return Double.parseDouble(faker.commerce().price());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}