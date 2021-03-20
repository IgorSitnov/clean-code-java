package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.stubs.AvailableProductStub;
import com.epam.engx.cleancode.functions.task2.stubs.UnavailableProductStub;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    private static final double DELTA = 0.0001;

    private Order order = new Order();

    @Test
    public void getProducts() {
        ArrayList<Product> products = getList(new AvailableProductStub(), new AvailableProductStub());
        order.setProducts(products);
        assertEquals(products, order.getProducts());
    }

    @Test
    public void orderContainsNoProduct() {
        order.setProducts(new ArrayList<Product>());
        assertEquals(0.0, order.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void orderContainsOnlyUnavailableProducts() {
        order.setProducts(getList(new UnavailableProductStub(), new UnavailableProductStub()));
        assertEquals(0.0, order.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void orderContainsTwoAvailableProducts() {
        order.setProducts(getList(new AvailableProductStub(), new AvailableProductStub()));
        assertEquals(20.0, order.getPriceOfAvailableProducts(), DELTA);
    }

    @Test
    public void orderContainsTwoAvailableProductsWithOtherUnavailableProducts() {
        order.setProducts(getList(new UnavailableProductStub(), new AvailableProductStub(),
                new AvailableProductStub(), new UnavailableProductStub()));
        assertEquals(20.0, order.getPriceOfAvailableProducts(), DELTA);
    }

    private ArrayList<Product> getList(Product... products) {
        return new ArrayList<>(Arrays.asList(products));
    }
}
