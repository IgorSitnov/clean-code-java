package com.epam.engx.cleancode.naming.collection;

import com.epam.engx.cleancode.naming.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.thirdpartyjar.Submitable;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectionOrderServiceTest {

    private static final int INFO_LEVEL = 4;
    private static final int CRITICAL_LEVEL = 1;

    private CollectionOrderServiceTestHelper helper = new CollectionOrderServiceTestHelper();

    private final Submitable collectOrderService = helper.getService();

    private final NotificationManagerMock notificationManagerMock = new NotificationManagerMock();

    @Test
    public void submitCollectionOrder() {
        helper.setCollectionService(collectOrderService, new TrueCollectionServiceStub());
        helper.setNotificationManager(notificationManagerMock, collectOrderService);

        helper.submit(collectOrderService);

        assertEquals(INFO_LEVEL, notificationManagerMock.level);
        Assert.assertEquals(Message.READY_FOR_COLLECT, notificationManagerMock.message);
    }

    @Test
    public void notSubmitCollectionOrder() {
        helper.setCollectionService(collectOrderService, new FalseCollectionServiceStub());
        helper.setNotificationManager(notificationManagerMock, collectOrderService);

        helper.submit(collectOrderService);

        assertEquals(CRITICAL_LEVEL, notificationManagerMock.level);
        Assert.assertEquals(Message.IMPOSSIBLE_TO_COLLECT, notificationManagerMock.message);
    }

}
