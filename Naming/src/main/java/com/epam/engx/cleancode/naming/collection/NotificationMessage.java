package com.epam.engx.cleancode.naming.collection;

public enum NotificationMessage {
    INFO (4), CRITICAL(1);

    private int level;

    public int getLevel() {
        return level;
    }

    NotificationMessage(int level) {
        this.level = level;
    }
}
