package com.coherent.store.xmlsort;

import com.coherent.domain.Product;
import com.coherent.store.Store;

import java.util.*;

public class ComparatorClass {
    Store store;
    private static final Map<String, Comparator<Product>> COMPARATOR_MAP = new LinkedHashMap<>() {{
        put("name", Comparator.comparing(Product::getName, String::compareToIgnoreCase));
        put("rate", Comparator.comparing(Product::getRate));
        put("price", Comparator.comparing(Product::getPrice));
    }};

    public ComparatorClass(Store store) {
        this.store = store;
    }

    private Comparator<Product> selectComparator(Map.Entry<String, String> mapEntry) {
        String comparatorType = mapEntry.getKey();
        Comparator<Product> selectedComparator = COMPARATOR_MAP.getOrDefault(comparatorType, null);
        if ((selectedComparator != null) && mapEntry.getValue().equalsIgnoreCase("desc")) {
            return selectedComparator.reversed();
        }
        return selectedComparator;
    }

    private Comparator<Product> buildComparator(Map<String, String> xmlMap) {
        try {
            return xmlMap.entrySet().stream()
                    .map(this::selectComparator)
                    .filter(Objects::nonNull)
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getSortedProducts() {
        XMLparser xmlParser = new XMLparser();
        Map<String, String> xmlMap = xmlParser.getMap();
        Comparator<Product> comparator = buildComparator(xmlMap);
        List<Product> products = store.getProducts();
        try {
            products.sort(comparator);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        printProducts(products);
    }

    public void getTop5Products() {
         List<Product> top5Products = store.getProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(5)
                .toList();
        printProducts(top5Products);
    }

    private void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.print(product.toString());
        }
    }
}