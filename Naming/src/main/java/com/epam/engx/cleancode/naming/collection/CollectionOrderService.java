package com.epam.engx.cleancode.naming.collection;


import com.epam.engx.cleancode.naming.OrderService;
import com.epam.engx.cleancode.naming.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.thirdpartyjar.Order;

public class CollectionOrderService implements OrderService {

    private CollectionService collectionService;
    private NotificationManager notificationManager;

    public void submitOrder(Order order) {
        if (collectionService.isEligibleForCollection(order)) {
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, NotificationMessage.INFO);
        } else {
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, NotificationMessage.CRITICAL);
        }
    }

    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
