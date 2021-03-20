package com.epam.engx.cleancode.naming.delivery;

import com.epam.engx.cleancode.naming.OrderService;
import com.epam.engx.cleancode.naming.thirdpartyjar.*;

import java.util.List;

public class DeliveryOrderService implements OrderService {

    private DeliveryService deliveryService;

    private OrderFulfilmentService orderFulfilmentService;

    public void submitOrder(Order order) {
        if (deliveryService.isDeliverable(order)) {
            List<Product> products = order.getProducts();
            orderFulfilmentService.fulfilOrder(products);
        } else {
            throw new NotDeliverableOrderException();
        }
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setOrderFulfilmentService(OrderFulfilmentService orderFulfilmentService) {
        this.orderFulfilmentService = orderFulfilmentService;
    }
}