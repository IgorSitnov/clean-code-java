package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        double orderPrice = 0.0;
        List<Product> availableProducts = getAvailableProducts(new ArrayList<>(products));
        for (Product product : availableProducts)
            orderPrice += product.getProductPrice();
        return orderPrice;
    }

    private List<Product> getAvailableProducts(List<Product> products) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (!product.isAvailable())
                iterator.remove();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
